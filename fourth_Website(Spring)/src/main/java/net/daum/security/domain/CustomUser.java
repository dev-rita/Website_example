package net.daum.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import net.daum.vo.MemberVO;

public class CustomUser extends User{
	private MemberVO member;
	
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		//검색된 아이디, 비번, 권한 목록을 람다식으로 수집한 다음 생성자를 호출해서 전달하여 객체화 한다.
		
		this.member=vo;
	}
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		//GrantedAuthority 상속받은 자손 타입으로만 제네릭 타입 형변환을 허용하면서 권한목록을 구함.
		
		super(username,password,authorities);//조상의 오버로딩 된 생성자를 호출하면서 아이디, 비번, 권한목록을 전달
	}
}
