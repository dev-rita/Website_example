package com.naver.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.MemberService;
import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;
import com.naver.vo.ZipcodeVO2;

import pwdconv.PwdChange;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//로그인 페이지
	@GetMapping("/member_login") //get 으로 접근하는 매핑주소를 처리, member_login 매핑주소 등록
	public ModelAndView member_login() {
		ModelAndView loginM=new ModelAndView();
		loginM.setViewName("member/member_login"); //뷰페이지 경로가 /WEB-INF/views/member/member_login.jsp
		return loginM;
	}//member_login()
	
	//회원가입 폼
	@RequestMapping("/member_join")
	public String member_join(Model m) {
		String[] phone= {"010","011","019"};
		String[] email = {"daum.net","naver.com","nate.com","google.com","직접입력"};
		
		m.addAttribute("phone",phone);
		m.addAttribute("email",email);
		return "member/member_join";
	}//member_join()
	
	
	//아이디 중복 검색
	@PostMapping("/member_idcheck") //post로 접근하는 매핑주소를 처리
    public String member_idcheck(String id,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		MemberVO db_id = this.memberService.idCheck(id);//아이디 중복 검색
		
		int re=-1;//중복 아이디가 없을 때 반환값
		
		if(db_id != null) {//중복 아이디가 있는 경우
			re=1;
		}
		
		out.println(re);//값 반환
		
		return null;
	}//member_idcheck()
	
	
	//우편번호 검색폼
	@RequestMapping("/zip_find")
	public String zip_find() {
		return "member/zip_find";
	}//zip_find()
	
	
	//우편검색 결과
	@RequestMapping("/zip_find_ok")
	public ModelAndView zip_find_ok(String dong) {
		List<ZipcodeVO> zlist=this.memberService.zipFind("%"+dong+"%");//%는 오라클 와일드 카드 문자로서 검색에서 하나이상의 모르는 문자와 매핑 대응한다.
		
		List<ZipcodeVO2> zlist2=new ArrayList<>();
		
		for(ZipcodeVO z:zlist) {
			ZipcodeVO2 z2=new ZipcodeVO2();
			
			z2.setZipcode(z.getZipcode());//우편번호
			z2.setAddr(z.getSido()+" "+z.getGugun()+" "+z.getDong());//시도 구군 동을  저장
			
			zlist2.add(z2);
		}
		
		ModelAndView zm=new ModelAndView("member/zip_find");
		zm.addObject("dong",dong);
		zm.addObject("zipcodelist",zlist2);
		return zm;
	}//zip_find_ok()
	
	
	//회원저장
	@RequestMapping("/member_join_ok")
	public String member_join_ok(MemberVO m) {//MemberVO빈클래스의 변수명과 member_join.jsp의 네임피라미터 이름이 동일하면 m에  입력한 회원정보가 저장됨.
		m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd()));//비번 암호화
		this.memberService.insertMember(m);//회원저장
		/* 문제)탈퇴 사유와 탈퇴 날짜는 빼고,mem_state=1 가입회원으로 해서 회원저장되게 만들고,개발자 테스트까지 마무리한다. 
		 */
		return "redirect:/member_login";//회원 저장후 로그인 매핑주소로 이동
	}//member_join_ok()
	
	
	//비번찾기 폼
	@GetMapping("/pwd_find")
	public String pwd_find() {
		return "member/pwd_find";
	}//pwd_find()
	
	
	//비번찾기 결과
	@PostMapping("/pwd_find_ok")
	public ModelAndView pwd_find_ok(String pwd_id,@RequestParam("pwd_name") String pwd_name,HttpServletResponse response,
			MemberVO m) throws Exception{
		//@RequestParam("pwd_name")은 request.getParameter("pwd_name")과 같다.즉 pwd_name 피라미터 이름에 저장된 회원이름을 가져온다.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();//출력스트림 객체 out생성
		
		m.setMem_id(pwd_id); m.setMem_name(pwd_name);
		MemberVO pm=this.memberService.pwdMember(m);//회원아이디와 이름을 기준으로 오라클로 부터 회원정보 검색
		
		if(pm == null) {
			out.println("<script>");
			out.println("alert('회원정보를 찾을 수 없습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			Random r=new Random();//Random클래스는 난수를 발생시킴
			int pwd_random=r.nextInt(100000);//임의의 정수 숫자 난수를 발생 시킴
			String ran_pwd=Integer.toString(pwd_random);//임시 정수 비번을 문자열로 변경
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(ran_pwd));//임시 비번을 암호화
			
			this.memberService.updatePwd(m);//임시 비번을 수정
			
			ModelAndView fm=new ModelAndView();
			fm.addObject("ran_pwd",ran_pwd);
			fm.setViewName("member/pwd_find_ok");
			return fm;
		}
		return null;
	}//pwd_find_ok()
	
	
	//로그인 인증
	@RequestMapping("/member_login_ok")
	public String member_login_ok(String login_id,String login_pwd,HttpSession session,HttpServletResponse response)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		MemberVO dm=this.memberService.loginCheck(login_id);
		//가입 회원 1인 경우만 로그인 인증 처리
		
		if(dm==null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!dm.getMem_pwd().equals(PwdChange.getPassWordToXEMD5String(login_pwd))) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
			}else {
				session.setAttribute("id",login_id);//세션 아이디 저장
				return "redirect:/index";
			}
		}
		return null;
	}//member_login_ok()
	
	
	//로그인 인증 후 메인화면
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String id=(String)session.getAttribute("id");
		if(id == null) {
		  out.println("<script>");
		  out.println("alert('다시 로그인 하세요!');");
		  out.println("location='member_login';");
		  out.println("</script>");
		}else {
          return "member/member_login";			
		}
		return null;
	}//index()
	
	
	//로그아웃
	@PostMapping("/member_logout")
	public String member_logout(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		session.invalidate();//세션 만료 즉 로그아웃 처리
		
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다!');");
		out.println("location='member_login';");
		out.println("</script>");
		
		return null;
	}//member_logout()
	
	
	//회원정보 수정폼
	@RequestMapping("/member_edit")
	public ModelAndView member_edit(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String id=(String)session.getAttribute("id");
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else {
			String[] phone= {"010","011","019"};
			String[] email = {"daum.net","naver.com","nate.com","google.com","직접입력"};
			MemberVO m=this.memberService.getMember(id);//오라클로 부터 아이디에 해당하는 회원정보를 가져옴.
			
			ModelAndView em=new ModelAndView("member/member_edit");
			em.addObject("phone",phone);
			em.addObject("email",email);
			em.addObject("m",m);
			return em;
		}
		return null;
	}//member_edit()
	
	
	//정보 수정 완료
	@RequestMapping("member_edit_ok")
	public String member_edit_ok(MemberVO m, HttpServletResponse response, HttpSession session) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        String id=(String)session.getAttribute("id");
        if(id == null) {
        	out.println("<script>");
        	out.println("alert('다시 로그인 하세요!');");
        	out.println("location='member_login';");
        	out.println("</script>");
        }else {
            m.setMem_id(id);
            m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd()));//정식 비번 암호화
            
            this.memberService.editMember(m);//정보 수정
            /* 문제)아이디를 기준으로 비번,이름,우편번호,주소,폰번호,멜을 수정되게 하고 개발자 테스트까지 마무리한다. 
             */
            
            out.println("<script>");
            out.println("alert('정보 수정했습니다!');");
            out.println("location='member_edit';");
            out.println("</script>");
        }
		return null;
	}//member_edit_ok()
	
	
	//회원탈퇴 폼
	@RequestMapping("/member_del")
	public ModelAndView member_del(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String id=(String)session.getAttribute("id");
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else{
			MemberVO dm=this.memberService.getMember(id);
			
			ModelAndView m=new ModelAndView();
			m.addObject("m",dm);
			m.setViewName("member/member_del");
			return m;
		}
		return null;
	}//member_del()
	
	
	//탈퇴 완료
	@RequestMapping("/member_del_ok")
	public String member_del_ok(HttpServletResponse response,HttpSession session,String del_pwd,String del_cont)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String id=(String)session.getAttribute("id");
		
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else {
			del_pwd=PwdChange.getPassWordToXEMD5String(del_pwd);//비번을 암호화
			MemberVO db_pwd=this.memberService.getMember(id);//오라클로 부터 비번을 가져옴.
			
			if(!db_pwd.getMem_pwd().equals(del_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				MemberVO dm=new MemberVO();
				dm.setMem_id(id); dm.setMem_delcont(del_cont);
				this.memberService.delMem(dm);//회원탈퇴
				/* 문제)아이디를 기준으로 탈퇴사유,mem_state=2,탈퇴날짜를 수정되게 하고,개발자 테스트까지 마무리 한다. 
				 */
				
				session.invalidate();//세션 만료. 즉 로그아웃
				
				out.println("<script>");
				out.println("alert('회원 탈퇴 했습니다!');");
				out.println("location='member_login';");
				out.println("</script>");
			}
		}
		return null;
	}//member_del_ok()
	
}




























