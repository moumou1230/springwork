package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class ThymeleafController {
	private EmpService empService;
	
	@GetMapping("thymeleaf")
	public String thymeleafTest(Model model) {//model은 클라이언트까지 전달이 아니고 page까지 전달?
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "test";
	}
}
