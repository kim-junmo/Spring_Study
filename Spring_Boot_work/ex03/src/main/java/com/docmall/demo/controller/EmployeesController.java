package com.docmall.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.EmployeeVO;
import com.docmall.demo.service.EmployeesService;

@RequestMapping("/employee/*")
@Controller
public class EmployeesController {
	
	//로그객체
	private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);

	/*
	 스프링에서 다른 클래스 또는 인터페이스를 사용하기 위하여
	 아래와 같은 객체생성작업을 하지 않는다.
	 EmployeesServiceimpl emp = new EmployeesServiceimpl():
	 emp.emp_list();
	 */
	
	@Autowired
	private EmployeesService employeeService;
	
	@GetMapping("emp_list") // /employee/emp_list  /employee/emp_list.jsp
	public void emp_list(Model model) {
		
		List<EmployeeVO> emp_list = employeeService.emp_list();
	
		//model.addAttribute("jsp에서 참조할 이름", 데이터);
		model.addAttribute("emp_list", emp_list); //emp_list는 이름이 동일하지 않아도 괜찮다.
		
	
	
	}
	
	
}
