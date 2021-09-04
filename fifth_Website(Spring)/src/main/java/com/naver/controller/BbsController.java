package com.naver.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.BbsService;
import com.naver.vo.BbsVO;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	//자료실 글쓰기
	@GetMapping("/bbs_write") //get으로 접근하는 매핑주소를 처리, bbs_write라는 매핑주소 등록
	public ModelAndView bbs_write(HttpServletRequest request) {
		
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));//쪽번호를 정수 숫자로 변경해서 저장시킴.
		}
		
		ModelAndView wm=new ModelAndView();
		wm.addObject("page",page);//page 속성 키이름에 페이지 번호 저장=>페이징에서 책갈피 기능 구현 목적
		wm.setViewName("bbs/bbs_write"); // 뷰리졸브 경로=> /WEB-INF/views/bbs/bbs_write.jsp
		return wm;
	}//bbs_write()
	
	//자료실 저장
	@PostMapping("/bbs_write_ok") //post로 접근하는 매핑주소 처리
	public String bbs_write_ok(BbsVO b,HttpServletRequest request) throws Exception{
		String saveFolder=request.getRealPath("resources/upload");//톰캣에서 인식하는 실제 이지파일 업로드 서버 경로
		int fileSize=5*1024*1024;//이진파일 최대크기
		MultipartRequest multi=null;//이진파일을 받을 변수선언
		
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		String bbs_name=multi.getParameter("bbs_name");
		String bbs_title=multi.getParameter("bbs_title");
		String bbs_pwd=multi.getParameter("bbs_pwd");
		String bbs_cont=multi.getParameter("bbs_cont");
		
		File upFile=multi.getFile("bbs_file");//첨부한 이진파일을 가져옴.
		
		if(upFile != null) {//첨부한 이진파일이 있는 경우
			String fileName=upFile.getName();//첨부한 파일명을 구함.
			Calendar c=Calendar.getInstance();//칼렌더는 추상클래스로 new로 객체 생성을 못함.하지만 연월일 시분초를 반환할 때 주소 사용
			int year=c.get(Calendar.YEAR);//년도값
			int month=c.get(Calendar.MONTH)+1;//월값, +1을 한 이유는 1월이 0으로 반환되기 때문이다.
			int date=c.get(Calendar.DATE);//일값
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//오늘날짜 폴더 경로를 저장
			File path01=new File(homedir);
			if(!(path01.exists())) {//폴더 경로가 존재하지 않다면
				path01.mkdir();//폴더 생성
			}
			Random r=new Random();
			int random=r.nextInt(100000000);//0이상 1억 미만 사이의 정수숫자 난수 발생
			
			/* 첨부파일 확장자 */
			int index=fileName.lastIndexOf(".");//.의 위치번호를 구함
			String fileExtendsion=fileName.substring(index+1);//.이후 부터 마지막 문자 까지 구함.즉 첨부파일 확장자를 구함.
			String refileName="bbs"+year+month+date+random+"."+fileExtendsion;//새로운 이진파일명
			String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;//오라클에 저장할 레코드값
			upFile.renameTo(new File(homedir+"/"+refileName));//실제 업로드
			b.setBbs_file(fileDBName);
		}else {
			String fileDBName="";
			b.setBbs_file(fileDBName);
		}
		
		b.setBbs_name(bbs_name); b.setBbs_title(bbs_title); b.setBbs_pwd(bbs_pwd); b.setBbs_cont(bbs_cont);
		
		this.bbsService.insertBbs(b);//자료실 저장
		
		return "redirect:/bbs_list";//자료실 목록보기로 이동
	}//bbs_write_ok()
	
	
	//자료실 목록(페이징과 검색기능)
	@RequestMapping("/bbs_list") //get or post로 접근하는 매핑주소를 처리
	public String bbs_list(Model listM, HttpServletRequest request, BbsVO b) {
	    //페이징
		int page=1;
		int limit=10;//한페이지에 보여지는 목록개수
	    if(request.getParameter("page") != null) {
	    	page=Integer.parseInt(request.getParameter("page"));//get으로 전달된 페이지번호를 정수 숫자로 변경해서 저장 시킴
	    }
	    
	    //검색기능
	    String find_field = request.getParameter("find_field");//검색 필드
	    String find_name = request.getParameter("find_name");//검색어
	    b.setFind_field(find_field);
	    b.setFind_name("%"+find_name+"%");//%는 쿼리문 검색기능에서 하나이상의 임의의 모르는 문자와 매핑 대응한다.
	    
	    int totalCount=this.bbsService.getListCount(b);//총 레코드 개수+검색후 레코드 개수
	    //System.out.println("총 레코드 개수 : "+totalCount);
	    
	    //페이징
	    b.setStartrow((page-1)*10+1);//시작행 번호
	    b.setEndrow(b.getStartrow()+limit-1);//끝행번호
	    
	    List<BbsVO> blist=this.bbsService.getBbsList(b);//검색 전후 페이징 목록
	    
	    //총페이지 수
	    int maxpage=(int)((double)totalCount/limit+0.95);
	    //시작페이지
	    int startpage=(((int)((double)page/10+0.9))-1)*10+1;
	    //마지막 페이지
	    int endpage=maxpage;
	    if(endpage > startpage+10-1) {
	    	endpage=startpage+10-1;
	    }
	    
	    listM.addAttribute("blist",blist);//blist속성 키이름에 목록을 저장
	    listM.addAttribute("page",page);//페이지 번호
	    listM.addAttribute("startpage",startpage);
	    listM.addAttribute("endpage",endpage);
	    listM.addAttribute("maxpage",maxpage);
	    listM.addAttribute("totalCount",totalCount);
	    listM.addAttribute("find_field",find_field);//검색 필드
	    listM.addAttribute("find_name", find_name);//검색어
	    
		return "bbs/bbs_list"; //뷰페이지 경로=>/WEB-INF/views/bbs/bbs_list.jsp
	}//bbs_list()
	
	//자료실 내용보기와 조회수 증가+답변폼+수정폼+삭제폼
	@GetMapping("/bbs_cont")
	public ModelAndView bbs_cont(int bbs_no,int page,String state,BbsVO b) {
		
		if(state.equals("cont")) {//내용보기 일때만 조회수가 증가 =>스프링의 AOP를 통한 트랜잭션 적용
			b=this.bbsService.getBbsCont(bbs_no);
		}else {//답변폼,수정폼,삭제폼일때는 조회수 증가를 안함.
		    b=this.bbsService.getBbsCont2(bbs_no);
		}
		
		String bbs_cont=b.getBbs_cont().replace("\n","<br/>");//textarea에서 엔터키를 친 부분을 줄바꿈 처리
		
		ModelAndView cm=new ModelAndView();
		cm.addObject("b",b);
		cm.addObject("bbs_cont",bbs_cont);
		cm.addObject("page",page);
		
		if(state.equals("cont")) {
			cm.setViewName("bbs/bbs_cont"); // /WEB-INF/views/bbs/bbs_cont.jsp
		}else if(state.equals("reply")) {//관리자 답변폼
			cm.setViewName("bbs/bbs_reply");
		}else if(state.equals("edit")) {//수정폼
			cm.setViewName("bbs/bbs_edit");
		}else if(state.equals("del")) {//삭제폼
			cm.setViewName("bbs/bbs_del");
		}
		return cm;
	}//bbs_cont()
	
	//관리자 답변 저장
	@RequestMapping("/bbs_reply_ok")
	public String bbs_reply_ok(BbsVO rb,int page) {
		//BbsVO rb라고 하면 BbsVO.java의 변수명과 bbs_reply.jsp의 네임 피라미터 이름이 같아서 rb에 값이 저장되어 있다.
		
		this.bbsService.replyBbs(rb);//답변저장
		return "redirect:/bbs_list?page="+page;
	}//bbs_reply_ok()
	
	
	//자료실 수정
	@RequestMapping("/bbs_edit_ok")
	public ModelAndView bbs_edit_ok(HttpServletRequest request,HttpServletResponse response,BbsVO b) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String saveFolder=request.getRealPath("resources/upload");//이진 파일 업로드 서버 경로
		int fileSize=5*1024*1024;//이진파일 업로드 최대크기
		
		MultipartRequest multi=null;//이진파일 받을 변수
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		int bbs_no=Integer.parseInt(multi.getParameter("bbs_no"));
		int page=1;
		if(multi.getParameter("page") != null) {
			page=Integer.parseInt(multi.getParameter("page"));
		}//히든으로 전달된 자료실 번호와 페이지 번호를 받아서 정수 숫자로 변경해서 저장시킴
		
		String bbs_name=multi.getParameter("bbs_name");
		String bbs_title=multi.getParameter("bbs_title");
		String bbs_pwd=multi.getParameter("bbs_pwd");
		String bbs_cont=multi.getParameter("bbs_cont");
		
		BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);//오라클로 부터 비번을 읽어옴.
		
		if(!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
		  out.println("<script>");
		  out.println("alert('비번이 다릅니다!');");
		  out.println("history.back();");//뒤로 한칸 이동. history.go(-1)과 같다.이전페이지로 이동
		  out.println("</script>");
		}else {
			File upFile=multi.getFile("bbs_file");
					
			if(upFile != null) {//첨부파일이 있는 경우
				String fileName=upFile.getName();//첨부한 파일명을 구함
				File delFile=new File(saveFolder+db_pwd.getBbs_file());//삭제할 파일 객체 생성
				if(delFile.exists()) {
					delFile.delete();//기존 첨부된 파일을 삭제
				}
				Calendar c=Calendar.getInstance();
				int year=c.get(Calendar.YEAR);//년도값
				int month=c.get(Calendar.MONTH)+1;//월값, +1을 한 이유는 1월이 0으로 반환 되기 때문이다.
				int date=c.get(Calendar.DATE);//일값
				
				String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
				File path01=new File(homedir);
				if(!(path01.exists())) {
					path01.mkdir();
				}
				
				Random r=new Random();
				int random=r.nextInt(100000000);
				
				//확장자
				int index=fileName.lastIndexOf(".");
				String fileExtendsion=fileName.substring(index+1);//첨부 파일 확장자
				String refileName="bbs"+year+month+date+random+"."+fileExtendsion;
				String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
				upFile.renameTo(new File(homedir+"/"+refileName));
				b.setBbs_file(fileDBName);
			}else {
				String fileDBName="";
				if(db_pwd.getBbs_file() != null) {//기존 첨부파일이 있는 경우
					b.setBbs_file(db_pwd.getBbs_file());					
				}else {
					b.setBbs_file(fileDBName);
				}
			}
			b.setBbs_no(bbs_no); b.setBbs_name(bbs_name); b.setBbs_title(bbs_title);
			b.setBbs_cont(bbs_cont);
			
			this.bbsService.editBbs(b);//자료실 수정
			/* 문제)서비스,dao,매퍼태그까지 번호를 기준으로 이름,제목,내용,첨부파일만 수정되게 하고,개발자 테스트 까지 완료한다. 
			 */
			
			ModelAndView em=new ModelAndView("redirect:/bbs_cont");
			em.addObject("bbs_no",bbs_no);
			em.addObject("page",page);
			em.addObject("state","cont");
			return em;// 주소 창에 노출되는 get방식으로 bbs_cont?bbs_no=번호&page=쪽번호&state=cont 로 3개의 인자값이 전달된다.
		}
		return null;
	}//bbs_edit_ok()
	
	
	//자료실 삭제
	@PostMapping("/bbs_del_ok") //post로 접근하는 매핑주소 처리
	public String bbs_del_ok(int bbs_no,int page,String del_pwd,HttpServletResponse response,HttpServletRequest request)
			throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String up=request.getRealPath("resources/upload");
		
		BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);
		
		if(!db_pwd.getBbs_pwd().equals(del_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.go(-1);");//history.back()과 같다. 이전 주소로 이동. 즉 뒤로 한칸 이동.
			out.println("</script>");
		}else {
			this.bbsService.delBbs(bbs_no);//자료실 삭제
			/* 문제) 번호를 기준으로 자료를 삭제되게 서비스,DAOImpl,매퍼태그까지 완성하고 개발자 테스트를 한다. 
			 */
			if(db_pwd.getBbs_file() != null) {//첨부파일이 있는 경우만 실행
				File file=new File(up+db_pwd.getBbs_file());//삭제할 파일 객체 생성
				file.delete();//폴더는 삭제되지 않고 기존 첨부파일만 삭제
			}
			return "redirect:/bbs_list?page="+page;
		}
		return null;
	}//bbs_del_ok()
	
}












