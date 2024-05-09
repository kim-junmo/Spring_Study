package com.docmall.demo.dto;

import org.springframework.web.util.UriComponentsBuilder;

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
	private String type; //검색종류 1.제목(T) 2.내용(C) 3.작성자(W) 4.제목 or 내용(TC) 5.제목 or 작성자(TW) 6.제목 or 작성자 or 내용(TWC) 
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

	//아래 메서드명은 getter메서드 이름 규칙대로 작성해야 한다. get(접두사) + typeArr(필드) = getTypeArr 메서드명
	//클라이언트로부터 검색정보가 (제목 또는 작성자 또는 내용)으로 선택되어 지면 type필드 TWC 제목 또는 작성자 또는 내용
	//type.split("")이 사용이 되면 "TWC".split("")이 되고 "T" "W" "C"의 배열 구조가 됨. 
	//
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	//UriComponentsBuilder : 여러개의 파라미터들을 연결하여 URL형태로 만들어주는 기능
	//?pageNum=2&amount=10&type=T&keyword=사과
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		
		return builder.toUriString();
				
	}
}


