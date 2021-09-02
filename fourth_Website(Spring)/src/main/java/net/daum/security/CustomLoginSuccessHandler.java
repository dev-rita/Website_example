package net.daum.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		System.out.println("Login Success");
		
		List<String> roleNames=new ArrayList<>();
		
		auth.getAuthorities().forEach(authority ->{
			roleNames.add(authority.getAuthority());
		});//로그인 한 사용자에 부여한 권한을 Authentication를 이용해서 사용자가 가진 모든 권한을 문자열로 체크한다. 
		//그리고 권한을 가져와서 컬렉션에 추가한다.
		
		System.out.println("ROLE NAMES:"+roleNames);//사용자 권한을 출력
		
		if(roleNames.contains("ROLE_ADMIN")) {//로그인 한 사용자들 중에서 관리자 권한을 가진 사람은 /sample/admin으로 이동
			response.sendRedirect("/sample/admin");
			return;//종료
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {//일반 회원이면
			response.sendRedirect("/sample/member");
			return;
		}
		
		response.sendRedirect("/");//루트 경로로 이동
	}

}
