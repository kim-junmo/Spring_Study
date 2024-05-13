package com.docmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;

public interface ReplyMapper {

	//Criteria - pageNum, amount (type, keyword 제외)
	//즉, pageNum, amount를 사용하려고 Criteria cri를 사용함.
	//Long bno는 글번호를 의미함.
	//Mapper인터페이스의 메서드 파라미터가 1개이면 그냥 사용하지만, 
	//Mapper인터페이스의 메서드 파라미터가 2개이상이면, 
	//@Param("이름")어노테이션을 꼭 사용해야됨(MyBatis 규칙)
	List<ReplyVO> getListPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	//댓글페이징 작업을 위한 전체 목록 작업
	int getCountByBno(Long bno);
	
	void insert(ReplyVO vo);
	
	void update(ReplyVO vo);
}






















