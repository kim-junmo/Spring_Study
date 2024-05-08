package com.docmall.demo.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class pageDTO {

	/*
	 		1	2	3	4	5	6	7	8	9	10	[다음]	<--첫번째 블럭
	 [이전]	11	12	13	14	15	16	17	18	19	20	[다음]	<--두번째 블럭
	 */
	
	//startPage: 페이지 넘기는 블럭에 가장 처음 값, 1 or 11 or 21....
	private int startPage; //각 블럭에서 출력할 시작페이지 번호
	private int endPage;   //각 블럭에서 출력할 끝페이지 번호
	//endPage: 페이지 넘기는 블럭에 가장 마지막 값, 10 or 20 or 30....
	
	private boolean prev, next;
	//prev: 이전버튼, next: 다음버튼
	
	private int total; //테이블의 건체 데이터 개수
	
	private Criteria cri; //cri.getPageNum(), cri.getAmount(), cri.getType(), cri.getKeyword()
	
	//생성자
	public pageDTO(Criteria cri, int total) {
		
		//생성자안에서 위의 5개 필드의 값을 구하는 작업
		this.cri = cri;
		this.total = total;
		
		//pageSize: 블럭마다 보여지는 페이지 번호의 개수
		//예 : 1 2 3 4 5 6 7 8 9 10
		int pageSize = 10;
		int endPageInfo = pageSize - 1; // 10 - 1 = 9
		
		//1 	2 	 3 	4 	5 6  7  8  9  10
		//11 	12	 13 14 15 16 17 18 19 20
		//cri.getPageNum -> 1 / (double) pageSize -> 10.0
		//일 경우 Math.ceil(1/10.0) * 10 / ceil은 올림 함수
		//cri.getPageNum -> 1		(int)(Math.ceil(1/10.0))  * 10 => 10
		//cri.getPageNum -> 9		(int)(Math.ceil(9/10.0))  * 10 => 10
		//cri.getPageNum -> 10		(int)(Math.ceil(10/10.0)) * 10 => 10
		//즉, 1~10까지를 10으로 나눈 후 올림을 하면 1이 나옴 여기에 10을 곱하는 것.
		//어떤 페이지 번호를 클릭하던 화면에 페이지 번호를 다시 출력하기 위하여 
		//endPage값이 동일하게 공식을 처리하는 것이 목적이다. 
		//즉 블럭에 1부터 10까지를 눌러도 10까지만 보이는 것임.
		
		//1 	2 	 3 	4 	5 6  7  8  9  10
		//11 	12	 13 14 15 16 17 18 19 20
		//아래 구문은 endPage의 값이 10, 20, 30 ...으로 고정이 되어 있음.
		this.endPage = (int) (Math.ceil(cri.getPageNum() / (double) pageSize)) * pageSize; 
		
		this.startPage = this.endPage - endPageInfo;
		
		//그러나 엔드페이지의 문제는 현재는 총 데이터 수에 맞는 endpage의 값을 구하지 못한 상태.
		
		//실제 총 데이터 수에 해당하는 블럭의 마지막 페이지 번호
		//total이라는 변수에 값이 33 이면, 여기에 실수로 만들기 위해 1.0을 곱해 33.0이 나옴
		//cri.getAmount는 10이므로 33.0에 10이면 3.30이 나옴. 
		//여기서 ceil로 올림이 되므로 4.0이 나오고 이를 int로 변환하면 4로 출력됨.
		int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));
		
		//아래 조건식이 true이면, endPage 변수의 값은 실제 테이블의 총 개수를 이용한 마지막 페이지 번호의 의미가 된다.
		if(realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		
		//[이전], [다음] 표시 여부
		this.prev = this.startPage > 1; //1 > 1이면 fales이므로 이전 버튼이 안나옴, 11>1은 ture이기 때문에 이전버튼이 나옴
		this.next = this.endPage < realEnd;

	}
}


