package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.pageDTO;
import com.docmall.demo.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {

	
	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//insert, delect, update는 String으로 한다.
	
	//의존성 주입
	@Autowired
	private BoardService boardService;
	
	//글쓰기 폼
	@GetMapping("write")
	public void write() {
		logger.info("called write...");
	}
	
	//글쓰기 저장
	@PostMapping("write")
	public String write(BoardVO vo) {
		
		logger.info("게시판 입력 데이터: " + vo);
		
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
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		
		//데이터소스(list)를 jsp에서 사용할 경우에는 파라미터를 Model을 사용한다.
		
		List<BoardVO> list = boardService.listwithPaging(cri);
		
		logger.info("게시물 목록 데이터: " + list);
		
		//1)게시물 목록 10건
		model.addAttribute("list", list);
		
		int total = boardService.getTotalCount();
		pageDTO pageDTO = new pageDTO(cri, total);
		
		logger.info("페이징 기능 데이터: " + pageDTO);
		
		//2)페이징 기능 : 1	 2	3	4	5	6	7	8	9	10 [다음]
		model.addAttribute("pageMaker", pageDTO);
		
	}
	
	
	//게시물조회, 게시물 수정
	@GetMapping(value = {"get","modify"})
	public void get(Long bno, Model model) {
		
		logger.info("게시물번호:" + bno);
				
		//(조회수 증가 작업 포함이 된) 게시물 정보 읽어오기.
		BoardVO boardVO = boardService.get(bno);
		model.addAttribute("boardVO",boardVO);
	}
	
	//게시물 수정하기
	@PostMapping("/modify")
	public String modify(BoardVO vo) {
		
		logger.info("수정데이터: " + vo);
		boardService.modify(vo);
		
		return "redirect:/board/list";
	}
	
	
	//게시물 삭제하기
	@GetMapping("delete")
	public String delete(Long bno) {
		
		logger.info("삭제 글번호:" + bno);
		boardService.delete(bno);
		
		return "redirect:/board/list";
	}
}
