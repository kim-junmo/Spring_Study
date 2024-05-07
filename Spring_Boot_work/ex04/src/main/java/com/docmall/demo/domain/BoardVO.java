package com.docmall.demo.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//bno, title, content, writer, regdate, updatedate, viewcount

@Getter
@Setter
@ToString
public class BoardVO {

	private Long bno; //참조타입의 long 클래스를 사용했기 때문에 Long을 사용해야 됨. Wrapper클래스
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int viewcount;
	
	/*
	public Long getBno() {
		return bno;
	}
	
	public void setBno(Long bno) {
		this.bno = bno;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public Date getUpdatedate() {
		return updatedate;
	}
	
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	public int getViewcount() {
		return viewcount;
	}
	
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + ", updatedate=" + updatedate + ", viewcount=" + viewcount + "]";
	}
	*/
	
}
