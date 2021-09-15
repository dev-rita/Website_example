package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.GongjiService;
import com.naver.vo.GongjiVO;

@Controller
public class GongjiController {

	@Autowired
	private GongjiService gongjiService;
	
	
	//메인화면 최신 공지 목록 5개 보기(사용자 공지사항)
	@GetMapping("/gongji_list")
	public ModelAndView gongji_list() {
		List<GongjiVO> glist=this.gongjiService.getGongjiList();//공지목록
		
		ModelAndView gm=new ModelAndView("gongji/gongji_list");
		gm.addObject("glist",glist);		
		return gm;
	}//gongji_list()
	
	//공지내용보기+조회수 증가
	@RequestMapping("/gongji_cont")
    public ModelAndView gongji_cont(int gongji_no) {
		GongjiVO g=this.gongjiService.getGcont(gongji_no);
		String gongji_cont=g.getGongji_cont().replace("\n","<br/>");//textarea태그에서 엔터키를 친부분은 다음줄로 줄바꿈
		
		ModelAndView cm=new ModelAndView();
		cm.addObject("g",g);
		cm.addObject("g_cont",gongji_cont);
		cm.setViewName("gongji/gongji_cont");
		return cm;
	}//gongji_cont()
}









