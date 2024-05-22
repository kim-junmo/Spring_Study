package com.docmall.demo.mapper;

import java.util.List;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;

public interface BoardMapper {
	
	void write(BoardVO vo);
	
	List<BoardVO> list();
	
	void modify(BoardVO vo);

	void delete(BoardVO vo);

	List<BoardVO> listwithPaging(Criteria cri);

	int getTotalCount(Criteria cri);
}
