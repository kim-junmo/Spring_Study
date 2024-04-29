package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@이름 : annotation(어노테이션)
//클라이언트(브라우저)로부터 요청을 받아 실행하는 스프링 클래스는 @Controller 어노테이션을 클래스 수준으로 적용해야 한다.
@Controller 
public class SampleController {
		
		private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
		
		//클라이언트(브라우저)에서 메서드를 호출하고자 할 경우에는 메핑주소를 설정해야 한다. 
		//이때 메핑주소 설정하는 어노테이션이 @RequestMapping("메핑주소")이다.
		@RequestMapping("/doA") //WEB-INF/views/ + "매핑주소" + .jsp -> /WEP-INF/views/doA.jsp
		public void doA() {
			logger.info("doA called...");
		}
		
		//매핑주소 /doB와 메서드명 doB는 일치할 필요가 없다.(상관없다)
		@RequestMapping("/doB")
		public void doB() {
			//System.out.println("doB called..."); : 성능상의 이유로 사용하지 않는다.
			logger.info("doB called...");
		}
		
		@RequestMapping("/doC")
		public void doC() {
			logger.info("doC called...");
		}
}
