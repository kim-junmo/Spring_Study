package com.docmall.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class SampleDTOList {
	
	//자바 컬렉션을 알아야 이해가 될 수 있음
	private List<SampleDTO> list; //list[0], list[1]
	
	//중요: 이걸 꼭 만들어야 한다. 이게 없으면 list가 null인 상태가 되기 때문
	public SampleDTOList() {
		list = new ArrayList<>(); 
	}

	public List<SampleDTO> getList() {
		return list;
	}

	public void setList(List<SampleDTO> list) {

		this.list = list;
	}

	@Override
	public String toString() {
		return "SampleDTOList [list=" + list + "]";
	}
	
	
}
