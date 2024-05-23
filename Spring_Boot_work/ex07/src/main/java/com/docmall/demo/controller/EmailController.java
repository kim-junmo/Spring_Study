package com.docmall.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.demo.dto.EmailDTO;
import com.docmall.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController //jsp를 사용하지 않는다. join.jsp에서 사용할 것이기 때문에.
@RequestMapping("/email/*")
public class EmailController {

	private final EmailService emailService;
	
	//HttpSession session : 웹 서버는 종료가 되면 인증번호가 지워지기 때문에 
	//서버측 메모리에 저장을 하기 위해서 사용함.
	//스프링이 밑의 작업을 자동으로 처리해준다.
	//EmailDTO dto = new EmailDTO();
	//dto.SetRecriverMail("입력한 메일주소");
	@GetMapping("/authcode")
	public ResponseEntity<String> authcode(EmailDTO dto, HttpSession session) {
		
		log.info("dto : " + dto); //dto.toString()이 호출된다.
		
		ResponseEntity<String> entity = null;
		
		String authcode = "";
		
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드: " + authcode);
		
		//사용자가 자신의 메일에서 발급받은 인증코드를 읽고, 회원가입시 인증확인란에 입력을 하게 되면, 
		//서버에서는 비교목적으로 세션방식으로 인증코드를 메모리에 저장해두어야 한다.
		//서버메모리에 authcode이름으로 authcode값을 저장해두었다.
		session.setAttribute("authcode", authcode); 
		
		try {
			emailService.sendMail(dto, authcode);
			entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200번 성공.
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); //500번오류
		}
		
		
		return entity;
	}
}
