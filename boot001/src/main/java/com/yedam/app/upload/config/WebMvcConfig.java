package com.yedam.app.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// boot가 가지고 있는 web 기본설정을 우리가 오버라이드 하겠다.(default라 강제성이 없어 오버라이드를 다안해줘도 된다?)
	// WebMvcConfigurer:spring boot 에서 제공해주는 인터페이스
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	//리소스 핸들링
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")//URI//static 안에 있는 폴더명과 겹치지 않게 잘못하면 충돌난다.
				.addResourceLocations("file:///" + uploadPath, "");//실제파일들이 있는 경로(,를 이용해서 경로 추가 가능) ,file부터 시작해서 프로토콜이 있어야된다?
	}

}
