package com.docmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.docmall.demo.domain.EmployeeVO;

@Mapper
public interface EmployeesMapper {
	
	//Employees테이블의 데이터 10건을 불러오는 기능의 추상 메서드
	//List<EmployeeVO> : Employees테이블의 읽어 온 데이터를 LIST컬렉션으로 저장하고자 한다.
	//목록 형태의 데이터들은 LIST컬렉션 저장소를 사용한다.  예> 상품목록, 주문목록, 게시판 목록, 회원목록 등등
	//배열은 길이를 정해야되기 때문에 배열을 사용하지 못하며 그렇기 때문에 리스트 컬렉션을 사용해야 한다.
	List<EmployeeVO> emp_list();

	
}
