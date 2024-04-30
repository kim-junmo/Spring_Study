package com.docmall.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 @RequestParam 예제, 
 */

@RequestMapping("/sample3/*")
@Controller
public class SampleController3 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	//클라이언트(브라우저)에서 사용하는 파라미터명과 스프링의 메서드 파라미터명이 일치하지 않을 때 중간에 @RequestParam("클라이언트 파라미터명")사용
	//매핑주소 localhost:9090/sample3/doA?name=홍길동&age=100
	@GetMapping("doA")
	public void doA(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		logger.info("이름 : " + name);
		logger.info("나이 : " + age);
	}
	
	//매핑주소 localhost:9090/sample3/doB?age=100
	//매핑주소 localhost:9090/sample3/doB?name=홍길동&age=100
	//required = false이면 데이터를 안받아도 된다. true면 데이터를 받아야 한다.
	@GetMapping("doB")
	public void doB(@RequestParam(value = "name", required = false, defaultValue = "이름없음") String name, int age) {
		logger.info("이름 : " + name);
		logger.info("나이 : " + age);
	}
	
	//매핑주소 localhost:9090/sample3/doC?age=100 -> 오류가 발생한다
	//매핑주소 localhost:9090/sample3/doC?name=홍길동&age=100 -> 정상적으로 작동한다.
	@GetMapping("doC") //@RequestParam(value = "name", required = true) 
	public void doC(@RequestParam(value = "name", required = true) String name, int age) {
		logger.info("이름 : " + name);
		logger.info("나이 : " + age);
	}
	
	//클라이언트쪽에서 동일한 파라미터명으로 여러개의 값을 서버로 받을 때 
	//매핑 주소는? localhost:9090/sample3/doD?num=1&num=2&num=3
	@GetMapping("doD")
	public void doD(@RequestParam("num") ArrayList<Integer> idx) {
		
		logger.info("idx : " + idx);
	}
	
	//클라이언트쪽에서 동일한 파라미터명으로 여러개의 값을 서버로 받을 때 
	//매핑 주소는? localhost:9090/sample3/doD?num=1&num=2&num=3
	@GetMapping("doE")
	public void doE(@RequestParam("num") int[] idx) {
		
		logger.info("idx : " + Arrays.toString(idx));
	}
	
	//클라이언트쪽에서 동일한 파라미터명으로 여러개의 값을 서버로 받을 때 
	//매핑 주소는? localhost:9090/sample3/doF?userId=user01&userId=user02&userId=user03
	
	@GetMapping("doF")
	public void doF(@RequestParam("userId") ArrayList<String> userId) {
		
		logger.info("userId : " + userId);
	}
	
	
}
