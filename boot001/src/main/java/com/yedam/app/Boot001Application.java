package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper")//메퍼를 구성하는 패키지를 읽어온다?
public class Boot001Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot001Application.class, args);
	}

}
