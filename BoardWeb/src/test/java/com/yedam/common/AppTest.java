package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.BoardMapper;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSession sqlSession = 
				DataSource.getInstance().openSession(true);
		BoardMapper boardMapper 
			= sqlSession.getMapper(BoardMapper.class);
		// 팩토리에서 세션을 한번에 가져왔다.
//		boardTest(boardMapper);
		
		serviceTest();
	}

	private static void serviceTest() {
		BoardService service = new BoardServiceImpl();
		SearchDTO search = new SearchDTO();
		
		search.setPage(1);
		search.setSearchCondition("T");
		search.setKeyword("java");
		service.boardList(search).forEach(System.out::println);
		
	}

	private static void boardTest(BoardMapper boardMapper) {
		BoardVO board = new BoardVO();
//		board조회
//		boardMapper.selectList().forEach(
//				boardVO -> {
//				System.out.println(boardVO);
//			});
//	
//		// 람다식 x 버전
//		List<BoardVO> list = boardMapper.selectList();
//		for (BoardVO boardVO : list) {
//			System.out.println(boardVO);
//		}
		
		
//		// board 추가
//		board.setTitle("제목");
//		board.setContent("추가 확인");
//		board.setWriter("유저");
//		
//		if(boardMapper.insertBoard(board) == 1) {
//			System.out.println("추가 성공");
//		}
//		
		boardMapper.selectList().forEach(boardVO -> {System.out.println(boardVO);});
//		
//		// board 수정
//		board.setTitle("수정");
//		board.setContent("내용 변경");
//		board.setBoardNo(12);
//		if(boardMapper.updateBoard(board) == 1) {
//			System.out.println("수정 성공");
//		}
//		
//		boardMapper.selectList().forEach(boardVO -> {System.out.println(boardVO);});
//		
//		// board 삭제
//		if(boardMapper.deleteBoard(12) == 1) {
//			System.out.println("삭제 성공");
//		}
	}
	
	
}
