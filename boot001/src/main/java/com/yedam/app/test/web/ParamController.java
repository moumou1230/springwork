package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;
@CrossOrigin(origins="*") //해당 컨트롤러에 모든 요청이 허용
@Controller
public class ParamController {
	// QueryString
	// Content-Type: Application/x-www-form-urlencode
	// Method : 상관없음
	
	// QueryString => 커맨드 객체(어노테이션x)  : 객체
	@RequestMapping(path="comobj", method = {RequestMethod.GET , RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path :/comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " +empVO.getLastName();
		result += "\t hiredate: " +empVO.getHireDate();
		return result;
	}
	// QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path = "reqparam", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName, //employeeId 필수(@RequestParam때문에) , lastName 생략가능
				               @RequestParam(name="message", defaultValue = "No Message",required = true) String msg) {
		String result = "";
		result += "path : /reqparam \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	//@PathVariable : 경로에 값을 포함하는 방식, 단일값 (vo랑 같이 사용하지 않는다)
	@RequestMapping("path/{id}") //path:Hong , path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) {//필수값
		String result = "";
		result += "path: /path/{id} \n";
		result += "\t id : " +id;
		return result;
	}
	
	//@RequestBody: Json 포맷, 배열 or 객체
	@RequestMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	@RequestMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
	
	
}
