package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter //필드 사용가능하게 userVO
public class LoginUserVO implements UserDetails {// 로그인했을때(인스턴스를 생성하면서) 딱 한번 값을 가지는게 끝이라서 @data랑 @setter사용하면 안됨
	
	private UserVO userVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {//권한에 대한 정보 
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {//계정 만료 여부
		return true;//실행이 계속 되어야해서
	}

	@Override
	public boolean isAccountNonLocked() {//계정 잠금 여부
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {//계정 비밀번호 만료 여부
		return true;
	}

	@Override
	public boolean isEnabled() {//계정 사용 여부
		return true;
	}
	
}
