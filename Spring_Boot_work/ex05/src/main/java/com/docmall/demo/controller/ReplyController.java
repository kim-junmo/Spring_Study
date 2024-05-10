package com.docmall.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.dto.pageDTO;
import com.docmall.demo.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//댓글 기능과 관련된 매핑주소를 관리하는 클래스
//게시판의 get.jsp에서 작업에 필요한 내용.

//jsp가 필요없기 때문에 어노테이션은 restController를 사용함.
//웹페이지가 기존 Board로 만들어져있기 때문에 필요없다. 
//그래서 jsp파일도 필요없으며 get.jsp에서 모든 작업이 끝남.

@Slf4j //log 객체지원
@RequiredArgsConstructor //final 필드를 이용하여 생상자를 생성한다. 그리고 그 생성자가 주입받는다
@RestController
@RequestMapping("/replies/*")
public class ReplyController {

	private final ReplyService replyService;
	
	//클라이언트의 요청주소: 1) /replies/pages/511/1 REST API기술이론에서 권장하는 주소형태, 2) /replies?pages=1&bno=511 전통적인 주소
	///replies/pages/511/1 주소(경로)에 존재하는 값을 사용할 때 구분되는 위치에 {이름}을 사용해야 한다.
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> getList(@PathVariable("bno")Long bno, @PathVariable("page") int page) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		// 1)댓글 목록 작업
		Criteria cri = new Criteria(page, 5); //댓글 목록에 첫 패이지, 페이지마다 10개씩 보여주겠다는 뜻.
		List<ReplyVO> list = replyService.getListPaging(cri, bno);
		map.put("list", list);
		
		//2) 댓글페이징 작업
		int total = replyService.getCountByBno(bno);
		pageDTO pageDTO = new pageDTO(cri, total); 
		
		log.info("" + pageDTO);
		map.put("pageMaker", pageDTO);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity;
	}
	

}
