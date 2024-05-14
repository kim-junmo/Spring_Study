package com.docmall.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //생성자 주입방식
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	//의존성 주입을 생성자 주입방식으로 사용하여 필드주입방식을 사용하지 않아도 된다.
	private final BoardService boardService;
	
	
	//게시판 글 쓰기 폼 Service -> Mapper 작업이 필요없다.
	@GetMapping("/write") //write.jsp가 필요하다.
	public void write() {
		log.info("called writer....");
	}
	
	//게시판 목록
	@PutMapping("/write")
	public String write(BoardVO vo) {
		log.info("게시판 입력데이터 " + vo);
		
		return red
	}
	
	//게시판 조회
	
	//게시판 수정
	
	//게시판 삭제
	
	
	
}
