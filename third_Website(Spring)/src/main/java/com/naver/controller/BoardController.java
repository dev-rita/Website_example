package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.service.BoardService;
import com.naver.vo.BoardVO;

@Controller //스프링 컨트롤러로 인식하게 함
@RequestMapping("/board/*") //컨트롤러 자체에 매핑주소 등록
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//스프링 MVC 게시판 글쓰기 폼
	@RequestMapping(value="/board_write",method=RequestMethod.GET) //GET으로 접근하는 board_write 매핑주소를 처리
	public void board_write(HttpServletRequest request) {
		//리턴 타입이 없어면 매핑주소가 곧 뷰페이지 파일명 즉 jsp파일명이 된다. 뷰페이지 경로가 곧 뷰리졸브 경로->/WEB-INF/views/board/board_write.jsp
		
		//책갈피 기능구현
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);//속성키이름 page에 페이지번호 저장
	}//board_write()
	
	//게시판 저장
	@RequestMapping(value="/board_write_ok",method=RequestMethod.POST) //post로 접근하는 매핑주소를 처리
	public ModelAndView board_write_ok(BoardVO b,RedirectAttributes rttr) {
	//BoardVO b는 board_write.jsp 의 네임피라미터 이름과 BoardVO.java의 변수명이 같으면 b에 글쓴이,글제목,글내용이 저장되어 있다.
		
		this.boardService.insertBoard(b);//게시판 저장
		rttr.addFlashAttribute("result","success");//result키이름에 success문자를 담아서 다른 매핑주소로 전달한다. 주소창에 노출 되지 않아서 보안상 좋다.
		
		ModelAndView wm=new ModelAndView();
		wm.setViewName("redirect:/board/board_list");//다른 매핑주소 board_list로 이동
		return wm;
	}//board_write_ok()
	
	//게시판 목록
	@GetMapping("/board_list") //get으로 전달하는 매핑주소를 처리
	public String board_list(Model m,HttpServletRequest request, @ModelAttribute BoardVO b) {
		
		/* 페이징 관련 소스 추가 */
		int page=1;//현재 페이지 번호
		int limit=10;//한페이지에 보여지는 목록 개수
		
		if(request.getParameter("page") != null) {//get으로 전달된 쪽번호가 있는 경우 실행
			page=Integer.parseInt(request.getParameter("page"));//페이지 번호(쪽번호)를 정수 숫자로 변경해서 저장
		}
		b.setStartrow((page-1)*10+1);//시작행 번호
		b.setEndrow(b.getStartrow()+limit-1);//끝행 번호
		
		int totalCount=this.boardService.getTotalCount();//총 레코드 개수
		//System.out.println("총레코드 개수:"+totalCount);
		List<BoardVO> blist=this.boardService.getBoardList(b);//게시물 목록
		//System.out.println("목록 개수:"+blist.size());
		
		//총페이지
		int maxpage=(int)((double)totalCount/limit+0.95);
		//현재 페이지에 보여질 시작페이지
		int startpage=(((int)((double)page/10+0.9))-1)*10+1;
		//현재 페이지에 보여질 마지막 페이지
		int endpage=maxpage;
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		m.addAttribute("list",blist);//list속성 키이름에 목록저장
		m.addAttribute("totalCount",totalCount);//totalCount키이름에 총 레코드 개수 저장
		m.addAttribute("startpage",startpage);
		m.addAttribute("endpage",endpage);
		m.addAttribute("maxpage",maxpage);
		m.addAttribute("page",page);
		
		return "board/board_list";//뷰페이지 경로가 /WEB-INF/views/board/board_list.jsp
	}//board_list()
	
	//조회수 증가+내용보기
	@RequestMapping("/board_cont") //get or post 방식으로 접근하는 매핑주소를 처리
	public ModelAndView board_cont(@RequestParam("bno") int bno,int page,String state,@ModelAttribute BoardVO b) {
		//@RequestParam("bno")는 request.getParameter("bno")와 같다.즉 bno피라미터이름에 담겨져서 전달된 번호값을 구함. int page도 get으로 전달된 페이지번호를
		//구함
		if(state.equals("cont")) {//게시판 목록 제목에서 클릭한 내용보기일때만 조회수 증가
		b=this.boardService.getBoardCont(bno);//번호에 해당하는 레코드값을 가져오고,조회수 증가
		}else {//게시판 목록 제목에서 클릭한 내용보기가 아닌경우는 조회수 증가 안됨
		b=this.boardService.getBoardCont2(bno);	
		}
		
		ModelAndView cm=new ModelAndView();
		cm.addObject("b",b);//b키이름에 b객체를 저장
		cm.addObject("page",page);//책갈피 기능을 구현하기 위해서 쪽번호를 저장
		cm.setViewName("board/board_cont");//뷰리졸브 즉 뷰페이지 경로->/WEB-INF/views/board/board_cont.jsp
		return cm;
	}//board_cont()
	
	//게시판 수정폼
	@GetMapping("/board_edit")
	public String board_edit(int bno,int page,Model m) {
		BoardVO eb=this.boardService.getBoardCont2(bno);//수정폼에서는 조회수 증가 되면 안된다.
		
		m.addAttribute("b",eb);
		m.addAttribute("page",page);
		return "board/board_edit";//뷰페이지 경로->/WEB-INF/views/board/board_edit.jsp
	}//board_edit()
	
	//수정완료
	@RequestMapping("/board_edit_ok") //get or post로 접근하는 매핑주소 board_edit_ok를 처리
	public String board_edit_ok(@ModelAttribute BoardVO eb,RedirectAttributes rttr,int page) {
		//@ModelAttribute BoardVO eb 하면 피라미터 이름과 BoardVO.java의 변수명이 같으면 쉽게 값을 가져올 수 있다.page는 변수명이 없기 때문에 별도로 가져와야 한다.
		this.boardService.editBoard(eb);//게시판 번호를 기준으로 글쓴이,제목,내용을 수정한다.
		rttr.addFlashAttribute("result","success");
		return "redirect:/board/board_cont?bno="+eb.getBno()+"&page="+page+"&state=editok";
		//get 방식으로 ?bno=번호&page=쪽번호&state=editok 가 각각 전달된다.
	}//board_edit_ok()
	
	//삭제완료
	@RequestMapping("/board_del")
	public ModelAndView board_del(int bno,int page,RedirectAttributes rttr) {
		this.boardService.delBoard(bno);//게시물 삭제
		/* 문제)board.xml에 delete 유일아이디명을 b_del하고 번호를 기준으로 게시물을 삭제하는 서비스,DAO,매퍼태그까지 완성하자. 
		 */
		
		rttr.addFlashAttribute("result","success");
		return new ModelAndView("redirect:/board/board_list?page="+page);
	}//board_del()
}

