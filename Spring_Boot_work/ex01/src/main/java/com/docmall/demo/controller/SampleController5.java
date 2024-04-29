package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 1)다름 메핑 주소로 이동하는 방법
 2)RedirectAttributes 인터페이스 사용하기 / addAttribute()메서드 사용하는 방법 or addFlashAttributr()메서드 사용하는 방법
 */


@Controller
public class SampleController5 {

	//로그 객체 생성
	private static final Logger logger = LoggerFactory.getLogger(SampleController5.class);
	
	//클라이언트에서 /doL이라는 주소를 요청하고, 
	//메서드가 실행된 후 jsp가 동작하는 것이 아니라 다른 매핑주소(/doM)로 이동하는 작업
	//1) 메서드의 리턴타입은 String이어야 한다.
	//2) 리턴값은 "redirect:/매핑주소"형식으로 작성해야 한다.
	
	@RequestMapping("/doL")
	public String doL(RedirectAttributes rttr) {
		
		logger.info("call doL...");
		
		rttr.addAttribute("String","spring study");
		rttr.addAttribute("idx","10");

		// /doM?title=spring study&idx=10
		
		//return "redirect:/doM?title=spring study&idx=10"; //jsp파일 명으로 해석하지 않는다. 
		
		return "redirect:/doM";
	}
	
	@RequestMapping("/doM") //메핑주소가 jsp파일 명이 된다.
	public void doM(String title, int idx) {
		
		logger.info("리다이렉트 요청으로 실행됨");
		logger.info("title: " + title);
		logger.info("idx: " + idx);
	}
	
	
	
	/***************************************************************************************/
	
	@RequestMapping("/doN")
	public String doN(RedirectAttributes rttr) {
		
		rttr.addFlashAttribute("msg", "ok");
		
		return "redirect:/doO"; //(redirect:/)를 사용시 jsp파일명이 동작되지 않는다.
	}
	
	@RequestMapping("/doO") // doO.jsp파일에서 msg키 이름을 사용가능
	public void doO() {
		
	}
	
	
	
}
