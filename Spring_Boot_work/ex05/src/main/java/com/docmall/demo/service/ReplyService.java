package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;

public interface ReplyService {

	List<ReplyVO> getListPaging(Criteria cri, Long bno);
	
	//댓글페이징 작업을 위한 전체 목록 작업
	int getCountByBno(Long bno);
	

	void insert(ReplyVO vo);
	
	void update(ReplyVO vo);
	
	void delete(Integer rno);
}
