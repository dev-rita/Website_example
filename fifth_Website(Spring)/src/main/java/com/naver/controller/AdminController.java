package com.naver.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.AdminService;
import com.naver.vo.AdminVO;

import pwdconv.PwdChange;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//관리자 로그인 폼
	@GetMapping("/admin_login") //get으로 접근하는 매핑주소를 처리
	public ModelAndView admin_login() {
		return new ModelAndView("admin/admin_login");// 뷰페이지 경로가 /WEB-INF/views/admin/admin_login.jsp
	}//admin_login()
	
	
	//관리자 로그인 인증과 암호화
	@PostMapping("/admin_login_ok")
	public String admin_login_ok(AdminVO ab,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd()));//관리자 비번을 암호화
		//ab.setAdmin_name("관리자");
		//this.adminService.insertAdmin(ab);//관리자 아이디,암호화 된 비번,관리자 이름을 저장
		
		AdminVO admin_pwd=this.adminService.adminLoginCheck(ab.getAdmin_id());//관리자 로그인 체크
		
		if(admin_pwd == null) {
           out.println("<script>");
           out.println("alert('관리자 정보가 없습니다!');");
           out.println("history.go(-1);");//history.back()과 같다.이전 주소 즉 이전창으로 이동
           out.println("</script>");
		}else {
			if(!admin_pwd.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비번이 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				session.setAttribute("admin_id",ab.getAdmin_id());//세션키이름 admin_id에 관리자 아이디 저장
				session.setAttribute("admin_name",admin_pwd.getAdmin_name());//세션키이름 admin_name에 관리자 이름 저장
				
				return "redirect:/admin_main";//관리자 메인화면으로 이동
			}
		}
		return null;
	}//admin_login_ok()
	
	
	//관리자 메인
	@RequestMapping("/admin_main")
    public ModelAndView admin_main(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id");//세션관리자 아이디를 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 관리자로 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			ModelAndView am=new ModelAndView();
			am.setViewName("admin/admin_main");//뷰페이지 경로 설정=> /WEB-INF/views/admin/admin_main.jsp
			return am;
		}
		return null;
	}//admin_main()
	
	
	//관리자 로그아웃
	@RequestMapping("/admin_logout")
	public String admin_logout(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		session.invalidate();//세션 만료 로그아웃
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다!');");
		out.println("location='admin_login';");
		out.println("</script>");
		
		return null;
	}//admin_logout()
}











