package net.daum.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
public class MemberTests {
	@Setter(onMethod_ = @Autowired) //setter() 메소드를 통한 DI 의존성 주입
	private PasswordEncoder pwencoder;
	
	@Setter(onMethod_ = @Autowired)
	private DataSource ds; //커넥션 풀 관리 ds
	
	@Test
	public void testInsertMember() {
		String sql="insert into tbl_member1 (userid,userpw,username) values(?,?,?)";
		
		for(int i=0;i<100;i++) {
			Connection con=null;//데이터 베이스 연결 con
			PreparedStatement pstmt=null;//쿼리문 수행
			
			try {
				con=ds.getConnection();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setNString(2, pwencoder.encode("pw"+i));//멤버 암호화
				
				if(i<80) {
					pstmt.setNString(1, "user"+i);
					pstmt.setNString(3, "일반 사용자"+i);
				}else if(i<90) {
					pstmt.setNString(1, "manager"+i);
					pstmt.setString(3,"운영자"+i);
				}else {//90~99
					pstmt.setNString(1, "admin"+i);
					pstmt.setString(3,"관리자"+i);
				}
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}//for
	}//testInsertMember()
	
	@Test
	public void testInsertAuth() {
		String sql="insert into tbl_member1_auth (userid,auth) values(?,?)";
		
		for(int i=0;i<100;i++) {
			
			Connection con=null;
			PreparedStatement pstmt=null;
			
			try {
				con=ds.getConnection();//커넥션 풀로 con생성
				pstmt=con.prepareStatement(sql);
				
				if(i<80) {//0~79
					pstmt.setNString(1, "user"+i);
					pstmt.setNString(2, "ROLE_USER");
				}else if(i<90) {
					pstmt.setNString(1, "manager"+i);
					pstmt.setNString(2, "ROLE_MEMBER");
				}else {
					pstmt.setNString(1, "admin"+i);
					pstmt.setNString(2, "ROLE_ADMIN");
				}
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt !=null) pstmt.close();
					if(con !=null) con.close();
				}catch(Exception e) {e.printStackTrace();}
			}			
		}//for
	}//testInsertAuth()
}
