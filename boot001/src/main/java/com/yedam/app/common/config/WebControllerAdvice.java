package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //이클래스 내부에있는 내용이 모든 컨트롤러에 동시에 지원된다.
public class WebControllerAdvice {
	
	@ModelAttribute("contextPath")//모든 컨트롤러에서 사용하는 변수등록(@ControllerAdvice 밑에 있는 ModelAttribute 는), 전역변수 선언
	public String contextPath(final HttpServletRequest request) {
		return request.getContextPath();
	}
}
