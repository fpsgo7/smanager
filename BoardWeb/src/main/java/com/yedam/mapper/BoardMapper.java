package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.ScheduleVO;

/**
 * 그록록, 등록, 수정, 삭제, 단건 조회, 
 * 여기서는 기능만 정의한다
 * 구현은 BoardMapper.xml 에서 한다.
 */
public interface BoardMapper {
	/* 조회 파트 */
	List<BoardVO> selectList();
	// 앞으로는 전체조회는 이걸 사용한다.
	List<BoardVO> selectListPaging(SearchDTO searchDTO);// 페이지 정보-> 5건씩 출력
	// 페이징 계산하기 위한 전체 건수
	int selectTotalCount(SearchDTO searchDTO);
	BoardVO selectBoard(int boardNo);
	
	/* 등록 파트 */
	int insertBoard(BoardVO board);
	
	/* 수정 파트 */
	int updateBoard(BoardVO board);
	/* 삭제 파트 */
	int deleteBoard(int boardNo);

	/* full Calendar 활용 */
	List<ScheduleVO> selectScheduleList();
	int insertSchedule(ScheduleVO vo);
	Object selectSchedule(ScheduleVO scheduleVO);
}
