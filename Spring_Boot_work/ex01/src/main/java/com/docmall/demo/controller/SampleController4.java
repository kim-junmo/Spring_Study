package com.docmall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.demo.domain.ProductVO;

@Controller
public class SampleController4 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	//메서드의 파라미터에 제공한 값을 jsp에서 사용하고 싶을 경우 @ModelAttrubute를 사용해야 한다.
	//이때 @ModelAttribute의 값과 testJ el문법의 값이 동일해야한다. 
	// http://localhost:9090/doJ?name=홍길동&age=100
	@RequestMapping("/doJ")
	public String doJ(@ModelAttribute("name") String name, @ModelAttribute("age") int age) {
		logger.info("이름은? " + name);
		logger.info("나이은? " + age);
		
		return "testJ";
	}
	
	//product 객체가 가지고 있던 혹은 객체의 정보를 jsp파일에서 사용하고 싶을 경우
	@RequestMapping("/doK")
	public String doK(Model model) {
		
		//실제로는 DB에서 상품테이블에서 정보를 생성해 옴.
		ProductVO product = new ProductVO("사과", 10000);

		logger.info("상품정보는? " + product); //product.tostring()메서드 호출

		//model.addAttribute("JSP에서 참조할 이름", 객체); 즉 참조할 이름은 달라도 괜찮음
		model.addAttribute("product", product);

		return "productInfo";
	}
	
	
	
}
