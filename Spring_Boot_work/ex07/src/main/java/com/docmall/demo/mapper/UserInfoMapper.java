package com.docmall.demo.mapper;

//UserInfoMapper.xml mapper파일 구성이 정상이여야 한다.
public interface UserInfoMapper {

	//아이디 중복체크
	String idCheck(String u_id);
}
