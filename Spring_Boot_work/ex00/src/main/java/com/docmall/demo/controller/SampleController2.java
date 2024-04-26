package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	//매핑주소와 메서드명은 일치 할 필요는 없다.
	//매핑주소를 jsp명으로 사용할 경우에는 메서드의 리턴타입이 void이어야한다.
	//매핑주소를 jsp명으로 사용하지 않을 경우에는 메서드의 리턴타입을 String으로 하여 리턴값을 jsp명으로 사용한다.
	
	@RequestMapping("/doD")
	public String doB() {
		//return "jsp파일명";
		logger.info("called doD...");
		return "testD"; //"testD"가 jsp파일 명이 된다.
	}
	
	@RequestMapping("/doE")
	public String doE() {
		logger.info("called doE...");
		return "testE"; //"testE"가 jsp파일 명이 된다.
	}
	
	@RequestMapping("/doF")
	public String doF() {
		logger.info("called doF...");
		return "testF";  //"testF"가 jsp파일 명이 된다.
	}
}
