package com.naver.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.AdminMemberService;
import com.naver.vo.MemberVO;

import pwdconv.PwdChange;

@Controller
public class AdminMemberController {

	@Inject
	private AdminMemberService adminMemberService;


	//관리자 회원목록
	@GetMapping("/admin_member_list")
	public ModelAndView admin_member_list(HttpServletResponse response,HttpSession session,HttpServletRequest request,
			@ModelAttribute MemberVO m) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String admin_id=(String)session.getAttribute("admin_id");

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
			m.setFind_field(find_field);
			m.setFind_name("%"+find_name+"%");
			//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와
			//매핑 대응

			int listcount=this.adminMemberService.getListCount(m);
			//전체 레코드 개수 또는 검색전후 레코드 개수
			//System.out.println("총 게시물수:"+listcount+"개");

			m.setStartrow((page-1)*7+1);//시작행번호
			m.setEndrow(m.getStartrow()+limit-1);//끝행번호

			List<MemberVO> blist=this.adminMemberService.getMemberList(m);
			//검색 전후 회원목록

			//총페이지수
			int maxpage=(int)((double)listcount/limit+0.95);
			//현재 페이지에 보여질 시작페이지 수(1,11,21)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			//현재 페이지에 보여줄 마지막 페이지 수(10,20,30)
			int endpage=maxpage;
			if(endpage > startpage+10-1) endpage=startpage+10-1;

			ModelAndView listM=new ModelAndView();
			
			listM.addObject("blist",blist);
			//blist 키이름에 값 저장
			listM.addObject("page",page);
			listM.addObject("startpage",startpage);
			listM.addObject("endpage",endpage);
			listM.addObject("maxpage",maxpage);
			listM.addObject("listcount",listcount);	
			listM.addObject("find_field",find_field);
			listM.addObject("find_name", find_name);

			listM.setViewName("admin/admin_member_list");
			//뷰페이지 폴더경로와 파일명 지정	
            return listM;
		}
		return null;
	}//admin_member_list()
	
	
	//관리자 회원 상세정보와 수정폼
    @RequestMapping("/admin_member_info")
    public String admin_member_info(String mem_id,int page,String state,HttpServletResponse response,HttpSession session,Model am)
    throws Exception{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	String admin_id = (String)session.getAttribute("admin_id");
    	
    	if(admin_id == null) {
    		out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			MemberVO m=this.adminMemberService.getMem(mem_id);//회원아이디를 기준으로 오라클로 부터 회원정보를 가져옴.
			String del_cont="";
			if(m.getMem_delcont() != null) {//탈퇴 사유가 있는 경우
				del_cont=m.getMem_delcont().replace("\n","<br/>");//textarea영역에서 엔터키 친부분을 줄바꿈
			}
			String[] phone={"010","011","019"};
			String[] email= {"daum.net","naver.com","nate.com","google.com","직접입력"};
			
			am.addAttribute("phone",phone);
			am.addAttribute("email",email);
			am.addAttribute("m",m);
			am.addAttribute("del_cont",del_cont);
			am.addAttribute("page",page);
			
			if(state.equals("info")) {//회원 상세 정보 
				return "admin/admin_member_info";
			}else if(state.equals("edit")) {//수정폼
				return "admin/admin_member_edit";
			}
		}
    	return null;
    }//admin_member_info()
    
    //관리자에서 회원정보 수정    
    @PostMapping("/admin_member_edit")
    public ModelAndView admin_member_edit(MemberVO m,HttpServletResponse response,HttpSession session,int page) throws Exception{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	String admin_id = (String)session.getAttribute("admin_id");
    	
    	if(admin_id == null) {
    		out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd()));//수정할 비번 암호화
			
			this.adminMemberService.editM(m);//회원정보 수정
			
			out.println("<script>");
			out.println("alert('정보 수정했습니다!');");
			out.println("location='admin_member_info?state=edit&mem_id="+m.getMem_id()+"&page="+page+"';");
			out.println("</script>");
		}
    	return null;
    }//admin_member_edit()
    
    
    //관리자에서 회원 삭제
    @RequestMapping("/admin_member_del")
    public ModelAndView admin_member_del(int page,String mem_id,HttpServletResponse response,HttpSession session) 
    throws Exception{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	String admin_id = (String)session.getAttribute("admin_id");
    	
    	if(admin_id == null) {
    		out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		}else {
			
			this.adminMemberService.delMember(mem_id);//아이디를 기준으로 회원삭제
			/* 문제)아이디를 기준으로 회원을 삭제되게 하고,개발자 테스트까지 마무리 한다. 
			 */
			
			return new ModelAndView("redirect:/admin_member_list").addObject("page",page);
			//admin_member_list?page=쪽번호 형태의 get방식으로 번호값이 전달되어 져서 회원목록으로 이동한다.
		}
    	return null;
    }//admin_member_del()
}
















