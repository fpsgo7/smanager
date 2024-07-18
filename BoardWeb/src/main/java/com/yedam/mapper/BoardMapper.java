package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.BoardVO;

/**
 * 그록록, 등록, 수정, 삭제, 단건 조회, 
 * 여기서는 기능만 정의한다
 * 구현은 BoardMapper.xml 에서 한다.
 */
public interface BoardMapper {
	/* 조회 파트 */
	List<BoardVO> selectList();
	
	BoardVO selectBoard(int boardNo);
	
	/* 등록 파트 */
	int insertBoard(BoardVO board);
	
	/* 수정 파트 */
	int updateBoard(BoardVO board);
	/* 삭제 파트 */
	int deleteBoard(int boardNo);
	
	
}
