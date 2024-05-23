package com.docmall.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	//아이디 중복체크 기능
	@GetMapping("/idCheck")
	//스트링으로 사용한 이유 : 아이디 사용가능, 불가능을 문자열로 나타나기 위해서
	//String u_id: UserInfoVO에서 가지고 와서 사용 (이왕이면 이름을 동일하게)
	//예외처리 한 이유는 데이터 베이스를 이용할때 예외가 발생할 수 있기 때문
	public ResponseEntity<String> idCheck(String u_id) throws Exception {
		
		log.info("아이디 : " + u_id);
		
		ResponseEntity<String> entity = null;
		
		//아이디가 사용한지 안한지 체크하는 구문
		String idUse = "";
		if(userInfoService.idCheck(u_id) != null) {
			idUse = "no"; //사용불가능
		}else {
			idUse = "yes"; //사용가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}


}





















