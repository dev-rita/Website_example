package net.daum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import net.daum.dao.MemberMapper;
import net.daum.security.domain.CustomUser;
import net.daum.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService {
/* CustomUserDetailsService 별도의 인증/권한 체크를 하는 이유는 jsp등에서 단순히 사용자 아이디 
 * (스프링 시큐리티에서는 username) 정도가 아닌 사용자의 이름이나 이메일 같은 추가적인 정보를 이용하기 위해서다.
 */
	@Setter(onMethod_ = {@Autowired})//setter메소드를 통한 의존성 주입
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("전달되어진 아이디값:"+username);
		
		//아이디를 기준으로 회원정보를 검색
		MemberVO vo=this.memberMapper.read(username);
		System.out.println("오라클로부터 가져온 회원정보 출력:"+vo);
		
		return vo == null? null : new CustomUser(vo);//생성자를 호출해서 회원정보를 전달함. 그런 다음 생성된 객체 반환.
	}

}
