package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest //테스트환경에서 ioc 컨테이너를 생성해주는 역할
class Boot001ApplicationTests {
	@Autowired //필드 주입
	private EmpMapper empMapper;
	
//	@Test
//	void empList() {//전체조회
//		List<EmpVO> list = empMapper.selectAllList();
//		assertTrue(!list.isEmpty()); 
//		
//	}
//	@Test
//	void empInfo() {//단건조회
//		EmpVO empVO = new EmpVO();
//		empVO.setEmployeeId(100);
//		
//		EmpVO findVO = empMapper.selectEmpInfo(empVO);
//		assertEquals("King",findVO.getLastName());
//		
//	}
//	@Test
//	void empInsert() {
//		EmpVO empVO = new EmpVO();
//		empVO.setLastName("Hong");
//		empVO.setEmail("kdHong@google.com");
//		empVO.setJobId("IT_PROG");
//		
//		int result = empMapper.insertEmpInfo(empVO);
//		System.out.println("아이디 : "+empVO.getEmployeeId());
//		
//		assertEquals(1, result);
//	}
//	@Test
//	void empDelete() {
//		int result = empMapper.deleteEmpInfo(4322);
//		assertEquals(1, result);
//	}
	
	@Test
	public void empUpdate() {
		//1)대상 원래 데이터 조회 : 단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(4321);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		System.out.println(findVO);
		//2)사용자가 수정하는 내용 입력
		findVO.setLastName("gsdfill");
		
		//3)Update
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(1, result);
	}
	
}
