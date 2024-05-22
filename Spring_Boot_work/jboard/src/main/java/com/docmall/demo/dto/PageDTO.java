package com.docmall.demo.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class PageDTO {

	private int startPage;
	private int endPage;
	
	private boolean prev, next;
	
	private int total;
	
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		int pageSize = 10;
		int endPageinfo = pageSize - 1;
		
		this.endPage = (int) (Math.ceil(cri.getPageNum() /(double) pageSize)) * pageSize;
		
		this.startPage = this.endPage - endPageinfo;
		
		int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));
		
		if(realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		
		// 이전, 다음 버튼을 원활하게 동작하기 위해 만든 코드
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
				
	}
	
	
}
