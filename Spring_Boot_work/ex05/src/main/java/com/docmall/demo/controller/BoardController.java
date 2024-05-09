package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.pageDTO;
import com.docmall.demo.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그객체 지원
@RequestMapping("/board") //jsp(view) 파일을 관리하기 위한 board폴더 생성
@Controller //jsp파일이 필요하기 때문에 (웹페이지를 만들어야 해서) Controller를 사용해야함.
public class BoardController {

	
	//로그 객체
	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//insert, delect, update는 String으로 한다.
	
	//의존성 주입 : 필드주입방식
	@Autowired
	private BoardService boardService;
	
	//글쓰기 폼
	@GetMapping("write")
	public void write() {
		log.info("called write...");
	}
	
	//글쓰기 저장
	@PostMapping("write")
	public String write(BoardVO vo) {
		
		log.info("게시판 입력 데이터: " + vo);
		
		//db저장 작업.
		boardService.write(vo);

		return "redirect:/board/list";
		
	}
	
	/*글 목록
	@GetMapping("list")
	public void list(Model model) {
		
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		
		List<BoardVO> list = boardService.list();
		model.addAttribute("list", list);
		
		logger.info("리스트");
	}*/
	
	//글 목록

	//메서드의 파라미터를 Criteria cri를 사용한 이유? 
	//클라이언트로부터 pageNum, amount, type, keyword 필드의 값을 받기 위함이다.
	//처음에는 클라이언트로부터 받은 필드값이 없다. 기본생성자가 호출되어 필드값이 사용된다.
	//list의 정보는 클라이언트로부터 받아오게 되며
	//Criteria cri의 정보도 클라이언트에게 받아오게 되기 때문
	//정확하게는 페이지 번호를 사용자가 클릭하면 그 정보를 받아와야 함.
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		
	
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		
		List<BoardVO> list = boardService.listwithPaging(cri);
		
		log.info("게시물 목록 데이터: " + list);
		
		//1)게시물 목록 10건
		model.addAttribute("list", list);
		
		int total = boardService.getTotalCount(cri);
		pageDTO pageDTO = new pageDTO(cri, total);
		
		log.info("페이징 기능 데이터: " + pageDTO);
		
		//2)페이징 기능 : 1	 2	3	4	5	6	7	8	9	10 [다음]
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	
	//게시물조회, 게시물 수정
	@GetMapping(value = {"get","modify"})
	public void get(Long bno, @ModelAttribute("cri")Criteria cri,Model model) {
		
		log.info("게시물번호:" + bno);
				
		//(조회수 증가 작업 포함이 된) 게시물 정보 읽어오기.
		BoardVO boardVO = boardService.get(bno);
		model.addAttribute("boardVO",boardVO);
	}
	
	//게시물 수정하기
	@PostMapping("modify")
	public String modify(BoardVO vo, Criteria cri) {
		
		log.info("수정데이터: " + vo);
		boardService.modify(vo);
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	
	//게시물 삭제하기
	@GetMapping("delete")
	public String delete(Long bno, Criteria cri /*, RedirectAttributes rttr*/) {
		
		log.info("삭제 글번호:" + bno);
		boardService.delete(bno);
		/*
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		//redirect:/board/list?pageNum=2&amount=10&type=T&keyword=사과
		 
		 */
		return "redirect:/board/list" + cri.getListLink();
	}
}
