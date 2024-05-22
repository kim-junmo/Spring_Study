package com.docmall.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docmall.demo.domain.BoardVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	public BoardMapper boardMapper;

	@Override
	public void write(BoardVO vo) {
		// TODO Auto-generated method stub
		boardMapper.write(vo);
	}

	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return boardMapper.list();
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		boardMapper.modify(vo);
	}

	@Override
	public void delete(BoardVO vo) {
		// TODO Auto-generated method stub
		boardMapper.delete(vo);
	}

	@Override
	public List<BoardVO> listwithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.listwithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}
}
