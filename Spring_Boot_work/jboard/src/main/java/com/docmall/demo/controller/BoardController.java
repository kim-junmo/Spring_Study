package com.docmall.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.PageDTO;
import com.docmall.demo.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")

public class BoardController {

	@Autowired
	public BoardService boardService;
	
	
	//글 작성
	@GetMapping("/write")
	public void write() {
		log.info("write");
	}
	
	
	//글 저장
	@PostMapping("/write")
	public String write(BoardVO vo) {
		
		log.info("write");
		
		boardService.write(vo);
		
		return "redirect:/board/list";
	}
	
	
	//글 목록
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		List<BoardVO> list = boardService.listwithPaging(cri);
		
		log.info("글 목록");
		
		model.addAttribute("list",list);
		
		int total = boardService.getTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, total);
		
		log.info("페이지 기능" + pageDTO);
		
		//2)페이지 기능
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	
	//게시물 조회 및 수정
	@GetMapping(value = {"get", "modify"})
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get");
		
	}
	
	//글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo) {
		
		log.info("modify");
		
		boardService.modify(vo);
		
		return "redirect:/board/list";
	}
	
	
	//글 삭제
	@GetMapping("/delete")
	public void delete() {
		
		log.info("delete");
	}
	
	
}
