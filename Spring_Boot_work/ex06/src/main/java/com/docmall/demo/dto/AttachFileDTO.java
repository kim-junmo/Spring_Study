package com.docmall.demo.dto;

import lombok.Data;

//목적 : 클라이언트가 전송한 업로드된 파일 목록의 정보를 담기위해 존재하는 클래스.

@Data //@Data를 사용하면 getter, setter, tostring 등 종합적으로 다 만들어줌.
public class AttachFileDTO {
	
	private String 	uuid;		//중복되지 않는 파일명
	private String 	uploadPath;	//날짜를 이용한 업로드 폴더명
	private String 	fileName;	//클라이언트에서 보낸 파일명
	private boolean image;		//이미지 파일여부,  true: 이미지 파일, false(기본값) : 일반파일

}
