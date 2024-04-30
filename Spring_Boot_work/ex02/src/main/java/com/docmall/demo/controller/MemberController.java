package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.MemberVO;

@RequestMapping("/member/*")
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@GetMapping("join")
	public void join() {
		logger.info("회원가입");
	}
	
	@PostMapping("join")
	public String join(MemberVO vo) {
		logger.info("회원가입 데이터" + vo);
		return "redirect:/member/list";
	}
	
	@GetMapping("list")
	public void list() {
		logger.info("list");
	}
}
