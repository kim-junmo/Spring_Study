package com.docmall.demo.service;

import org.springframework.stereotype.Service;
import com.docmall.demo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor를 사용하면 Bean이 생성 (bean 이름 : userInfoServiceImpl)
@RequiredArgsConstructor
@Service 
public class UserInfoServiceImpl implements UserInfoService {

	private final UserInfoMapper userInfoMapper;
	
	/*
	public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}
	*/
	
	@Override
	public String idCheck(String u_id) {
		// TODO Auto-generated method stub
		return userInfoMapper.idCheck(u_id);
	}
	
	
	
	
	
	
	
}
