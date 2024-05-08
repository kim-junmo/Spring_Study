package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;

public interface BoardService {

	//메서드(); 글쓰기 저장
	void write(BoardVO vo);
	
	//글목록
	List<BoardVO> list();
	
	//검색조건이 추가되는 목록
	List<BoardVO> listwithPaging(Criteria cri);
	
	//검색조건이 포함된 테이블의 총 데이터 개수
	int getTotalCount(Criteria cri);
	
	//게시물 조회
	BoardVO get(Long bno);
	
	//글 수정하기
	void modify(BoardVO vo);
	
	//글 삭제하기
	void delete(Long bno);
}
