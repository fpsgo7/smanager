package com.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int boardNo;// 기본키
	private String title;
	private String content;
	private String writer;
	private int viewCnt;
	private Date writeDate;
	
	public int getBoardNo() {
		return boardNo;
	}
}
