package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.demo.domain.ProductVO;
/*
클라이언트 결과값을 jsp로 동작하여 받고자 하는 것이 아니라. JSON포멧의 데이터로 받고자 할 때의 학습예제
@ResponseBody 리스폰스바디 어노테이션을 사용하여 동작한다.
*/

@Controller
public class SampleController6 {

	//로그 객체 생성
	private static final Logger logger = LoggerFactory.getLogger(SampleController6.class);
	
	//productVO vo객체를 JSON으로 변환할 대 사용하는 라이브러리 : jackson-datebind라이브러리
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		
		ProductVO vo = new ProductVO("사과", 10000);
		
		logger.info("called doJSON..." + vo); //vo.toString()호출
		
		return vo; //참조타입 초기 값은 null이 가능함.
		//{"name":"사과","price":10000} JSON문자열이 클라이언트 (브라우저)로 응답한다.
	}
}
