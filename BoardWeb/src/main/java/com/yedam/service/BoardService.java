package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.ScheduleVO;

/**
 * MVC 패턴의 디자인에 따라서 mode 영역(service, dao:mapper)
 * 책: 4강 mvc 기반 웹 프로젝트 참고
 * 숙제 536 페이지 mvc에 대해 자세이 읽기
 */
public interface BoardService {
	// Service 계층이기에 업무로직 방식의 이름을 사용한다.!!!
	List<BoardVO> boardList(SearchDTO searchDTO);
	int totalCount(SearchDTO searchDTO);// 페이징 계산용 건수.
	boolean addBoard(BoardVO board);
	boolean modifyBoard(BoardVO board);
	boolean removeBoard(int boardNo);
	BoardVO getBoard(int boardNo);
	
	// FullCalendar 파트
	List<ScheduleVO> getSchedule();
	boolean addSchedule(ScheduleVO vo);
	boolean checkSchedule(ScheduleVO scheduleVO);
}
