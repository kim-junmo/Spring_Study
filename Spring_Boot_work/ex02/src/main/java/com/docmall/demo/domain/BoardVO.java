package com.docmall.demo.domain;

import java.util.Date;

/*
 데이터베이스의 게시판테이블을 저장하는 클래스

 CREATE TABLE BOARD (
    IDX 	NUMBER  		PRIMARY KEY,--글번호, 시퀀스 생성 및 사용
    TITLE   VARCHAR2(100)   NOT NULL, --제목
    CONTENT VARCHAR2(1000)  NOT NULL, --내용
    WRITER  VARCHAR2(50)    NOT NULL, --작성자
    REGDATE DATE   			DEFAULT SYSDATE   --등록일
);

1. 테이블명(BOARD)와 클래스명(Boardvo)의 이름은 동일하지 않아도 상관없다.
2. 테이블의 컬럼명과 클래스의 필드명은 동일해야하고 데이터 타입은 호환이 되어야 한다.
3. 필드는 private접근자 사용, getter/setter 메서드는 public, toString()메서드 재정의
 */

public class BoardVO {
	
	private Long	idx; //NUMBER는 int와 long을 사용할 수 있지만 long을 많이 사용하는 추세
	private String	title;
	private String	content;
	private String	writer;
	private Date	regdate;
	
	
	public Long getIdx() {
		return idx;
	}
	
	public void setIdx(Long idx) {
		this.idx = idx;
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

	//필드들을 통한 데이터 확인 목적을 가지고 있음.
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + "]";
	}
	

	
}
