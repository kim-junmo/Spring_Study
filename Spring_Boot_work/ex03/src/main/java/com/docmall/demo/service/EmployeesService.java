package com.docmall.demo.service;

import java.util.List;

import com.docmall.demo.domain.EmployeeVO;

public interface EmployeesService {

	//Mapper인터페이스의 메서드명과 동일하지 않아도 된다.
	List<EmployeeVO> emp_list();
}
