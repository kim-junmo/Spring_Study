package com.docmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.demo.domain.ReplyVO;
import com.docmall.demo.dto.Criteria;
import com.docmall.demo.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final 필드를 이용하여 생상자를 생성한다. 그리고 그 생성자가 주입받는다
@Service
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;

	/* 룸북 @RequiredArgsConstructor 어노테이션을 사용하면, 
	   아래와 같은 매개변수를 가진 생성자가 생성된다.
	   https://dev-coco.tistory.com/70
	public ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
	*/
	

	@Override
	public List<ReplyVO> getListPaging(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		return replyMapper.getListPaging(cri, bno);
	}

	@Override
	public int getCountByBno(Long bno) {
		// TODO Auto-generated method stub
		return replyMapper.getCountByBno(bno);
	}
	
}
