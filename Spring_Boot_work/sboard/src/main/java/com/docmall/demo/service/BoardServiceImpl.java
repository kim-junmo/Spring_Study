package com.docmall.demo.service;

import org.springframework.stereotype.Service;

import com.docmall.demo.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
}
