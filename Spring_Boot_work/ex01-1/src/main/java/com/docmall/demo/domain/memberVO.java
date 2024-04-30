package com.docmall.demo.domain;

import java.util.Date;

/*
 	ID			VARCHAR2(10 BYTE)	No					1	
	PWD			VARCHAR2(10 BYTE)	No					2	
	NAME		VARCHAR2(50 BYTE)	No					3	
	EMAIL		VARCHAR2(50 BYTE)	No					4	
	JOINDATE	DATE				Yes		"SYSDATE"	5	
 */
public class memberVO {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date   joindate;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getJoindate() {
		return joindate;
	}
	
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
	@Override
	public String toString() {
		return "memberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joindate=" + joindate
				+ "]";
	}
	
	

}
