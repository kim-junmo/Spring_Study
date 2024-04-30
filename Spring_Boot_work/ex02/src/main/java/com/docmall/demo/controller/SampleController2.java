package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;

//게시판 기능에 사용하는 컨트롤러 클래스
/*
 주소 
 글쓰기(get) /board/write
 글저장(post) /board/write
 글목록(get) /board/list
 */

@RequestMapping("/board/*")
@Controller
public class SampleController2 {

	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	//글쓰기, 글쓰기 폼 /board/write
	@GetMapping("write")
	public void write() {
		logger.info("글쓰기");
	}
	
	//글쓰기 저장 -> DB의 테이블에 insert, update, delete 작업을 하는 메핑주소는 현재까지는 @PostMapping으로 주소 설정한다.
	//method의 리턴 타입은 String으로 해야한다.
	
	//저장한 글은 보통 클라이언트에 안보여주고 서버에서 처리됨, 이후 글 목록을 보여줌
	//메서드 오버 로딩 문법을 이용하여 매개변수가 달라 write를 사용할 수 있게 됨.
	
	//글쓰기 저장 /board/write
	@PostMapping("write")
	public String write(BoardVO vo) {
		
		//사용자가 입력한 게시판 데이터를 BoardVO vo 받고, 데이터베이스 게시판테이블에 저장한다.
		
		logger.info("게시판 데이터: " + vo); //vo.toString을 호출
		
		return "redirect:/board/list";
	}
	
	//글목록 /board/list
	@GetMapping("list")
	public void list() {
		logger.info("리스트");
	}
}
