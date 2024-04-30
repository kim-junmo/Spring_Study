package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    https://thalals.tistory.com/268
	ResponseEntity 클래스 사용법 : 일반적으로 ajax기술을 이용할 때 사용. 
	서버에서 클라이언트에게 보내는 데이터와 그 데이터 설명에 해당하는 mime-type, 인코딩, 상태코드를 보내서 명시적으로 작업하기 위한 클래스이다.
 */

@RequestMapping("/sample5/*")
@Controller
public class SampleController5 {

	//로그객체
		private static final Logger logger = LoggerFactory.getLogger(SampleController5.class);	
		
		@GetMapping("doA")
		public @ResponseBody String doA() {
			
			String msg = "{\"name\" : \"홍길동\"}"; //json포멧의 문자열
			
			return msg;
		}
		
		@GetMapping("doB")
		public ResponseEntity<String> doB() {
			
			ResponseEntity<String> entity = null; //변수 선언
			
			//1)body : 서버에서 클라이언트에게 전송하는 데이터
			String msg = "{\"name\" : \"홍길동\"}"; //json포멧의 문자열
			
			//2)body 데이터를 해석하기 위한 어떤 Mime-type인지, 인코딩 정보를 작업.
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json;charset=utf-8");
			
			//3)상태코드 : HttpStatus.OK) -> 서버의 프로그램이 성공적으로 작동이 되면 ok
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			
			return entity;
		}
}
