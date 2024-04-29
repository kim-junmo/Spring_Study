package com.docmall.demo.dto;

public class LoginDTO {
	
	//필드명을 basicGet.jsp의 name의 파라미터 명
	//<input type="text" name="u_id"><br>, <input type="password" name="u_pw"><br> 
	//즉 u_id, u_pw와 일치해야 한다.
	private String u_id;
	private String u_pw;
	
	public String getU_id() {
		return u_id;
	}
	
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	
	public String getU_pw() {
		return u_pw;
	}
	
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}

	@Override
	public String toString() {
		return "LoginDTO [u_id=" + u_id + ", u_pw=" + u_pw + "]";
	}
	
	

}
