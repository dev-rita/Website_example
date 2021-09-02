package net.daum.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import net.daum.dao.MemberMapper;
import net.daum.vo.MemberVO;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberMapperTests {
	@Setter(onMethod_ =@Autowired) //setter()메소드를 통한 의존성 즉 DI주입
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		MemberVO vo=this.mapper.read("admin90");//admin90 회원 아이디에 대한 회원정보를 읽어온다.
		
		vo.getAuthList().forEach(authVO -> System.out.println(authVO));
	}
}
