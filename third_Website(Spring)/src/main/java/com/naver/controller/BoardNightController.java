package com.naver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.service.BoardNightService;
import com.naver.vo.BoardNightVO;

@Controller
public class BoardNightController {
	
	@Autowired
	private BoardNightService boardNightService;
	
	//게시판 글쓰기
	@RequestMapping(value="/board_insert",method=RequestMethod.GET)
	public void board_write() {
		
	}//board_write()
	
	//게시판 저장
		@RequestMapping(value="/board_ok",method=RequestMethod.POST)
		public ModelAndView board_ok(BoardNightVO nb, RedirectAttributes rttr) {
			this.boardNightService.insertBoard(nb);//게시판 저장
			rttr.addFlashAttribute("result","success");
			
			ModelAndView wm=new ModelAndView();
			wm.setViewName("redirect:/board_list");
			return wm; 
		}//board_write_ok()
		
		//게시판 목록
		@GetMapping("/board_list")
		public String board_list(Model m) {
			
			int totalCount=this.boardNightService.getTotalCount();
			m.addAttribute("totalCount",totalCount);
			return "/board_list";
		}//board_list()
}
