package com.docmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.service.UserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/userinfo/*")
@Slf4j
@Controller
//Bean생성 : userInfoController
@RequiredArgsConstructor
public class UserInfoController {
	
	//UserInfoServiceImpl 클래스를 사용하지 않고
	//UserInfoService 인터페이스를 사용한 이유는 다형성 목적
	private final UserInfoService userInfoService;
	
	//회원가입 폼
	@GetMapping("/join")
	public void joinForm() {
		log.info("called join...");
	}


}
