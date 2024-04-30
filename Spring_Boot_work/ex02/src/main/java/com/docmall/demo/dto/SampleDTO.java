package com.docmall.demo.dto;

public class SampleDTO {
	
	private String name;
	private int age;
	
	public SampleDTO() {
		System.out.println("SampleDTO 생성자 호출");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		System.out.println("setName호출");
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		System.out.println("setAge호출");
		this.age = age;
	}

	@Override
	public String toString() {
		return "SampleDTO [name=" + name + ", age=" + age + "]";
	}


	
	

}
