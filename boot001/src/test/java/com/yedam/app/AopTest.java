package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest
public class AopTest {
	@Autowired
	AaaService aaaService;
	
	//@Test
	void transcationalTest() {
		aaaService.insert();
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void pwdEncoderTest() {
		String password = "1234";
		
		//DB에 저장된 비밀번호 => 암호화 작업
		String enPwd = passwordEncoder.encode(password);
		System.out.println(enPwd);
		
		boolean result = passwordEncoder.matches(password, enPwd);
		System.out.println(result);
	}
}
