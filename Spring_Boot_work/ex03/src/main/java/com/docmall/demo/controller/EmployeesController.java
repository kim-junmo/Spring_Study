package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employee/*")
@Controller
public class EmployeesController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);
}
