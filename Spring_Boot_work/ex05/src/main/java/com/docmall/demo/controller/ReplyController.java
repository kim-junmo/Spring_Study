package com.docmall.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//rest API 개발 방법으로 작업

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
	
	//댓글 저장
	//consumes = "application/json" : 클라이언트에서 보내는 데이터는 json이어야 한다.
	//produces = {MediaType.TEXT_PLAIN_VALUE} : 서버에서 클라이언트로 보내는 응답데이터 text이다.
	//ResponseEntity<String> : 서버로 보내는 응답데이터가 text이기 때문에 String이다. 
	//String이기 때문에 get.jsp 중에 dateType는 text이며 data는 json으로 받아야 된다. 
	//@RequestBody : json데이터를 ReplyVO로 변환해주는 기능.jackson-databind 라이브러리가 실제 json관련 작업을 함.
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		
		log.info("댓글데이터" + vo);
		
		replyService.insert(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}

	//댓글 수정을 put or patch
	@PutMapping(value = "/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> Modify(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		
		log.info("댓글수정데이터" + vo);
		
		//댓글 수정작업
		replyService.update(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		
		return entity;
		
	
	}
	
	//댓글 삭제 delete 일반적인 주소(/delete?rno=댓글번호) X rest API 경로형태 주소(/delete/500)
	@DeleteMapping(value = "/delete/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("rno") Integer rno){
		ResponseEntity<String> entity = null;
		
		//댓글 삭제작업
		replyService.delete(rno);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
		
	}
}
