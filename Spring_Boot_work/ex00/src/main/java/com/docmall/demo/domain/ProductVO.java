package com.docmall.demo.domain;

//오라클 데이터베이스에 product 테이블을 생성하고 구조를 정의한 클래스
public class ProductVO {

	private String name; //상품 이름
	private double price; //상품 가격
	
	//일반적으로 생성자를 만들지 않지만 db가 준비 안되어 있어 생성자를 만듬
	
	public ProductVO(String name, double price) {
		//super();
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
	
	
	
}
