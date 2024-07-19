package com.yedam.common;

import lombok.Data;

@Data
public class PageDTO {
	private int page; // 현재페이지.
	private int startPage; // 시작페이지
	private int endPage;// 끝페이지
	private boolean prev, next; // 이전페이지와 이후 페이지여부를 알아보는 변수
	
	public PageDTO(int page, int totalCnt) { // page는 현제 페이지 totalCnt 는 현제 글수
		// page: 3 totalCnt :  37 일경우 1 2 3 4 5 6 7 8 prev 와 next 가 false 라서  << >> 없다.
		// page: 3 totalCnt : 88 일경우 1 2 3 4 5 6 7 8 9 10 >> prev만 false 라 << 가 없다.
		this.page = page;
		this.endPage = (int)(Math.ceil(page / 10.0) * 10); // 2*10
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)Math.ceil(totalCnt / 5.0);// 실제건수 계산한 마지막 페이지
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
