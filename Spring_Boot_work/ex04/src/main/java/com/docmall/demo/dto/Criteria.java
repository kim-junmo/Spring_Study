package com.docmall.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	//pageNum: 1, 2, 3, 4, 5 등 선택된 페이지 번호를 저장하기 위한 변수
	private int pageNum; 
	private int amount; 
	//amount: 게시물 5개, 10개, 20개, 30개, 50개 등 페이지마다 화면에 출력할 게시물 건수 
	
	//검색용도
	//type: 선택한 검색종류(제목만, 게시글, 게시글 + 댓글, 글작성자 등)
	private String type;
	private String keyword;
	//keyword: 박스에 검색한 검색어가 저장이 된다.
	
	//생성자 
	public Criteria() {
		this(1, 10);
	}
	
	//매개변수가 있는 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		
		System.out.println("pageNum: " + pageNum + ", amount " + amount);
	}
}
