package com.naver.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConTest {
	private static final String DRIVER="oracle.jdbc.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USER="night";
	private static final String PW="night";
	
	@Test
	public void testCon() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection con=DriverManager.getConnection(URL,USER,PW)){
			/* 자바 7에서 AutoCloseable 인터페이스를 구현 상속받은 자손을 try()내에서 객체를 생성하면 final문에서
			 * 명시적으로  close()하지 않아도 자동으로 닫힌다.
			 */
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
