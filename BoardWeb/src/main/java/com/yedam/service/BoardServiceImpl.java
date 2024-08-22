package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;
import com.yedam.vo.ScheduleVO;

/**
 * 인터페이스(BoardService)를 구현하는 구현 객체이다.
 * 인터페이스에 정의된 메서드를 다 구현해야함
 */
public class BoardServiceImpl implements BoardService{
	SqlSession sqlSession = 
			DataSource.getInstance().openSession(true);
	BoardMapper mapper 
		= sqlSession.getMapper(BoardMapper.class);
	@Override
	public List<BoardVO> boardList(SearchDTO searchDTO) {
		return mapper.selectList();
		//return mapper.selectListPaging(searchDTO);
	}

	@Override
	public int totalCount(SearchDTO searchDTO) {
		return mapper.selectTotalCount(searchDTO);
	}
	
	@Override
	public boolean addBoard(BoardVO board) {
		return mapper.insertBoard(board) == 1;
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return mapper.deleteBoard(boardNo) == 1;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return mapper.selectBoard(boardNo);
	}
	/* Full Calendar 파트 */
	@Override
	public List<ScheduleVO> getSchedule() {
		return mapper.selectScheduleList();
	}

	@Override
	public boolean addSchedule(ScheduleVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertSchedule(vo) == 1;
	}

	@Override
	public boolean checkSchedule(ScheduleVO scheduleVO) {
		// TODO Auto-generated method stub
		if(mapper.selectSchedule(scheduleVO) != null) {
			return true;
		}
		return false;
	}


	
}
