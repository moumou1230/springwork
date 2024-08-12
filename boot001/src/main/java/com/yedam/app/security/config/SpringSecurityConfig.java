package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity // springsecurity 작성할때
public class SpringSecurityConfig {

	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();// 비밀번호가 암호화된 상태로 로그인
	}

	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http// Security가 적용될 URI
				.authorizeHttpRequests((authrize) -> authrize.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						// permitAll은 인증받지 못한 모든사람 //경로에 권한이 붙는다.
						.requestMatchers("/", "/all").permitAll()// 경로(한번에 여러개의 경로에대해서 권한등록 가능)
						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")//.hasRole("USER") 이건 권한을 하나만 줄 수 있다.//이부분 떄문에 밑에 user쪽은 .roles("USER")
						.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")//이부분때문에 밑에 이걸로.authorities("ROLE_ADMIN").
						// 이경로로 접근하는 사람(인증 받은사람)의 권한을 정의
						.anyRequest().authenticated()// anyRequest가 else 느낌
				)
				.formLogin(formLogin -> formLogin.defaultSuccessUrl("/all"))// 로그인을 했을때 특정한 경로로 리다이렉트
				.logout(logout -> logout.logoutSuccessUrl("/all").invalidateHttpSession(true));// 로그아웃시 세션이 같이 삭제.
		return http.build();
	}

	/*@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {// 하나의 인스턴스를 만들어서 회원정보를 일시적으로 담는다
		UserDetails user =  User.builder()// 밑에는 초기값들을 세팅해주는?
								.username("user1")
								.password(passwordEncoder().encode("1234"))
								.roles("USER")// ROLE_USER
								// .authorities("ROLE_USER")
								.build();

		UserDetails admin = User.builder()
								.username("admin1")
								.password(passwordEncoder().encode("1234"))
								// .roles("ADMIN")//ROLE_ADMIN
								.authorities("ROLE_ADMIN","ROLE_USER").build();//위에 방법(33줄)을 할건지 이걸할건지는 선택

		return new InMemoryUserDetailsManager(user, admin);

	}*/
}
