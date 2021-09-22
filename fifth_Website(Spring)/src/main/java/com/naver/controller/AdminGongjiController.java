package com.naver.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.AdminGongjiService;
import com.naver.vo.GongjiVO;

@Controller
public class AdminGongjiController {

	@Autowired
	private AdminGongjiService adminGongjiService;


	//관리자 공지목록
	@RequestMapping("/admin_gongji_list")
	public String admin_gongji_list(HttpServletResponse response, HttpServletRequest request, HttpSession session,GongjiVO g,
			Model listM) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id = (String)session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			int page=1;//쪽번호
			int limit=7;//한페이지에 보여지는 목록개수
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));			
			}
			String find_name=request.getParameter("find_name");//검색어
			String find_field=request.getParameter("find_field");//검색
			//필드
			g.setFind_field(find_field);
			g.setFind_name("%"+find_name+"%");
			//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와
			//매핑 대응

			int listcount=this.adminGongjiService.getListCount(g);
			//전체 레코드 개수 또는 검색전후 레코드 개수
			//System.out.println("총 게시물수:"+listcount+"개");

			g.setStartrow((page-1)*7+1);//시작행번호
			g.setEndrow(g.getStartrow()+limit-1);//끝행번호

			List<GongjiVO> glist=this.adminGongjiService.getGongjiList(g);
			//전체 목록 or 검색 전후 목록

			//총페이지수
			int maxpage=(int)((double)listcount/limit+0.95);
			//현재 페이지에 보여질 시작페이지 수(1,11,21)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			//현재 페이지에 보여줄 마지막 페이지 수(10,20,30)
			int endpage=maxpage;
			if(endpage > startpage+10-1) endpage=startpage+10-1;

			listM.addAttribute("glist",glist);
			//glist 키이름에 값 저장
			listM.addAttribute("page",page);
			listM.addAttribute("startpage",startpage);
			listM.addAttribute("endpage",endpage);
			listM.addAttribute("maxpage",maxpage);
			listM.addAttribute("listcount",listcount);	
			listM.addAttribute("find_field",find_field);
			listM.addAttribute("find_name", find_name);

			return "admin/admin_gongji_list";
			//뷰페이지 폴더경로와 파일명 지정	
		}
		return null;
	}//admin_gongji_list()
	
	
	//관리자 공지 작성
	@GetMapping("/admin_gongji_write")
	public ModelAndView admin_gongji_write(int page,HttpSession session, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id=(String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			ModelAndView wm=new ModelAndView();
			wm.addObject("page",page);
			wm.setViewName("admin/admin_gongji_write");// /WEB-INF/views/admin/admin_gongji_write.jsp
			return wm;
		}
		return null;
	}//admin_gongji_write()
	
	
	//관리자 공지 저장
	@RequestMapping("/admin_gongji_write_ok")
	public String admin_gongji_write_ok(GongjiVO g, HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id=(String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			this.adminGongjiService.insertG(g);//관리자 공지 저장
			//문제)관리자 공지 저장되게 하고 개발자 테스트 까지 마무리한다.
			
			return "redirect:/admin_gongji_list";
		}
		return null;
	}//admin_gongji_write_ok()
	
	
	//관리자 공지 상세정보,공지 수정 폼
	@RequestMapping("/admin_gongji_cont")
	public String admin_gongji_cont(Model cm,int no,int page,String state,HttpSession session,HttpServletResponse response)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id = (String)session.getAttribute("admin_id");//관리자 세션 아이디를 구함.
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");			
		}else {
			GongjiVO g=this.adminGongjiService.getGongjiCont(no);
			String g_cont=g.getGongji_cont().replace("\n","<br/>");//textarea 영역에서 엔터키 친 부분을 다음줄로 줄바꿈 처리
			
			cm.addAttribute("g",g);
			cm.addAttribute("g_cont",g_cont);
			cm.addAttribute("page",page);
			
			if(state.equals("cont")) {//내용보기 즉 상세정보 보기
				return "admin/admin_gongji_cont";
			}else if(state.equals("edit")) {//수정폼
				return "admin/admin_gongji_edit";
			}
		}
		return null;
	}//admin_gongji_cont()
	
	
	//관리자 공지 수정완료
	@RequestMapping("/admin_gongji_edit_ok")
	public ModelAndView admin_gongji_edit_ok(GongjiVO g,int page,HttpServletResponse response,HttpSession session)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
	    String admin_id=(String)session.getAttribute("admin_id");
	    if(admin_id == null) {
	    	out.println("<script>");
	    	out.println("alert('관리자로 다시 로그인 하세요!');");
	    	out.println("location='admin_login';");
	    	out.println("</script>");
	    }else {
	    	this.adminGongjiService.updateGongji(g);//공지 수정
	    	/* 문제) 번호를 기준으로 공지작성자,제목,내용을 수정되게 하고,개발자 테스트까지 완료한다. 
	    	 */
	    	
	    	return new ModelAndView("redirect:/admin_gongji_list?page="+page);
	    }
		return null;
	}//admin_gongji_edit_ok()
	
	
	//관리자 공지 삭제
	@GetMapping("/admin_gongji_del")
	public String admin_gongji_del(int no,int page,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id = (String)session.getAttribute("admin_id");
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			this.adminGongjiService.deleteGongji(no);//관리자 공지 삭제
			/* 문제) 번호를 기준으로 공지를 삭제되게 하고, 개발자 테스트까지 한다. 
			 */
			
			return "redirect:/admin_gongji_list?page="+page;
		}
		return null;
	}//admin_gongji_del()
}








































