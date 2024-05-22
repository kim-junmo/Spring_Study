package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;

public interface BoardService {
	
	//글 작성
	void write (BoardVO vo);
	
	//글 목록
	List<BoardVO> list();
	
	//글 검색조건 추가 목록
	List<BoardVO> listwithPaging(Criteria cri);
	
	//글 수정
	void modify(BoardVO vo);
	
	//글 삭제
	void delete(BoardVO vo);

	//검색조건이 포함된 테이블의 총 데이터 수
	int getTotalCount(Criteria cri);

}
