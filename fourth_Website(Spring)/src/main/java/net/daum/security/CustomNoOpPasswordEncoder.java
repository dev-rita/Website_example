package net.daum.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	//스프링 5부터는 시큐리티에서 기본적으로 PasswordEncoder를 지정해줘야 한다.
	
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
		
	}//인코딩 되기 전 비번을 문자열로 반환

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		return rawPassword.toString().contentEquals(encodedPassword);
	}//인코딩 되기 전 비번과 인코딩 된 비번이 같은지 비교한다.
}
