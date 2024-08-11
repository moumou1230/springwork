package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	private DeptService deptService;
	
	@Autowired
	DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		model.addAttribute("depts" , list);
		
		return "dept/list";
	}
	
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept", findVO);
		
		return "dept/info";
	}
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	@PostMapping("deptInsert")
	public String deptInsert(DeptVO deptVO) {
		int deptId = deptService.deptInsert(deptVO);
		String url = null;
		if(deptId > -1) {
			url ="redirect:deptInfo?departmentId="+deptId;
		}else {
			url ="redirect:deptList";
		}
		return url;
	}
	
	@GetMapping("deptUpdate")
	public String deptUpdate(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/update";
	}
	@PostMapping("deptUpdate")
	public Map<String, Object> deptUpdateAjax(DeptVO deptVO){
		return deptService.deptUpdate(deptVO);
	}
	
	@GetMapping("deptDelete")
	public String dpetDelete(Integer departmentId) {//매개변수 자체가 key값 (객체가 아니고 단일 값이라서)
		deptService.deptDelete(departmentId);
		return "redirect:deptList";
	}

}
