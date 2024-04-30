package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.dto.SampleDTO;
import com.docmall.demo.dto.SampleDTOList;


/*
 SampleDTO 활용하는 예제
 */

@RequestMapping("/sample4/*")
@Controller
public class SampleController4 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);	
	
	@GetMapping("basicForm")
	public void basicForm() {
		logger.info("basicForm called...");
	}
	
	//기본생성자 호출 -> setAge(), setName() 각각호출되어, dto 객체가 가르키는 힙영역의 기억장소에 입력데이터가 저장
	@PostMapping("basicPro")
	public void basicPro(SampleDTO dto) {
		logger.info("단일 (1인) 회원정보 : " + dto);
	}
	
	/******************************************/
	
	@GetMapping("basicForm2")
	public void basicForm2() {
		logger.info("basicForm called...");
	}
	
	@PostMapping("basicPro2")
	public void basicPro2(SampleDTOList lst) {
		logger.info("다수 회원정보 : " + lst);
	}
	
	
}
