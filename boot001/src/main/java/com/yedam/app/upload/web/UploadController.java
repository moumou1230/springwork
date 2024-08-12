package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 해당컨트롤러 안에 로그가 들어온다?(bean)
@Controller
public class UploadController {
	
	@Value("${file.upload.path}")//1.실행을 할때 환경변수로 넘겨줄건가, 2.properties에서 넘겨줄건가(이거사용) // 파일 저장 경로를 필드에 값을 넣는다.
	private String uploadPath;//경로 설정은 외부에서 가져오는 형식인데 왜 그렇게 하지
	
	@GetMapping("formUpload")
	public void formUploadPage() {//경로를 기반으로 파일을 찾으려고 void사용, 경로 이름 그대로 파일명을 가져오는데? templates밑에있어서
		
	}
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] files) {//변수 이름은 html에서(input) 넘기는 이름,input쪽에서 multiple 작성이 되어있으면 배열로 받음
		log.info(uploadPath);
		for(MultipartFile file : files) {
			
			log.info(file.getContentType()); //slf4j ,,이미지인지 뭔지를 파일의 형태 파악
			log.info(file.getOriginalFilename()); //이름
			log.info(String.valueOf(file.getSize())); //사이즈 콘솔에 찍힘
			
			String fileName = file.getOriginalFilename();
			String saveName = uploadPath + File.separator + fileName; //java가 인식하는 경로(separator), "/"대신 경로로 쓴다?
			
			log.debug("saveName : " +saveName);
			
			Path savePath = Paths.get(saveName);//경로, File은 파일을 기반으로 하는 경로? 이거는 그냥 순수 경로?
												//Paths.get(saveName): String 으로 작성된걸 경로로 만들어준다 
			try {
				file.transferTo(savePath); //transferTo 매개변수로 넘겨준 경로를 기반으로해서 파일을 만들고 파일을 읽어주는 작업을 한다.
										   //multipart 경로를 우리가 지정한 경로로 작업 해준다.
			} catch (IOException e) {//외부(경로)와 작업을 하는거라서 예외처리 해줘야된다.
				e.printStackTrace();
			}
		}
		
		return "redirect:formUpload";
	}
	
	//AJAX 사용
	@GetMapping("upload")
	public void uploadPage() {}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){//이미지만 처리해야돼서 아닌것은 제한.
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String fileName = uploadFile.getOriginalFilename();//getOriginalFilename(): 경로 다빼고 파일명만 
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();//보통 년월일 3개로 폴더 나눠야됨.
	        
	        //UUID
	        String uuid = UUID.randomUUID().toString();//식별자, 고유ID, 완전고유 식별자라서 절대 중복이 되지않는다.
	        										   //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));//날짜를 특정 포멧으로 반환
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
