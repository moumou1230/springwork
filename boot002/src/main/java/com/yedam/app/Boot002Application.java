package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yedam.app.**.mapper") //mapper interface 위치
public class Boot002Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot002Application.class, args);
	}

}
