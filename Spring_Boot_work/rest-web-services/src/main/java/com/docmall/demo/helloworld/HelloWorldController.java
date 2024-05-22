package com.docmall.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//rest api
@RestController
public class HelloWorldController {

	// hello-world
	
	@GetMapping(path = "/hello-world")
	public String HelloWorld() {
		return "hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean HelloWorldbean() {
		return new HelloWorldBean ("hello World bean");
	}
}
