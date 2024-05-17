package com.docmall.demo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import jakarta.servlet.MultipartConfigElement;

/*
 스프링 부트 2.7에서는 Multipart가 기본 bean으로 등록되어 있다.
 스프링 부트 3 이상부터는 Multipart 설정 클래스를 생성하여, bean으로 등록해야한다.
 */

@Configuration //설정파일을 만들기 위한 어노테이션 or Bean을 등록하기 위한 어노테이션
public class MultipartConfig {

	//스프링에서 자동으로 관리가 된다. 리턴타입 multipartResolver bean 등록 및 관리.
	//new StandardServletMultipartResolver(); 객체 생성
	//@Bean : 라이브러리에서 제공하는 클래스를 스프링에서 관리함. 
	@Bean //스프링 시스템에서 객체를 관리하고 싶을 때 bean어노테이션을 사용하면 스프링에서 자동으로 bean이라는 성격으로 관리가 된다. 
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	/*
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(null);
		factory.setMaxRequestSize(null);
		factory.setMaxFileSize(null);
		
		return factory.createMultipartConfig();
	}
	*/
}
