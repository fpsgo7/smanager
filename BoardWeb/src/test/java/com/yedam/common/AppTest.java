package com.yedam.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.BoardMapper;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.BoardVO;
import com.yedam.vo.ReplyVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSession sqlSession = 
				DataSource.getInstance().openSession(true);
		BoardMapper boardMapper 
			= sqlSession.getMapper(BoardMapper.class);

//		boardTest(boardMapper);
		
//		serviceTest();

//		replyServiceTest();
		
		memberServiceTest();
	}

	private static void memberServiceTest() {
		MemberService svc = new MemberServiceImpl();
		List<Map<String,Object>> list = svc.getCountByMember();
		for (Map<String, Object> map : list) {
			System.out.println("---------------");
			Set<String> keyset = map.keySet();
			for(String key :  keyset) {
				System.out.printf("%s , %s\n",key,map.get(key));
			}
		}
		
	}

	private static void replyServiceTest() {
		ReplyService service = new ReplyServiceImpl();
		
		// 등록
		ReplyVO vo = new ReplyVO();
		vo.setBoardNo(123);
		vo.setReplyContent("내용");
		vo.setReplyer("user02");
		service.addRply(vo); // 성공
		
		// 삭제
//		service.removeReply(5); // 성공
		
		// 조회
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		SearchDTO dto = new SearchDTO();
		dto.setBno(123);
		dto.setPage(1);
		list = service.replyList(dto);
		list.forEach(System.out::println);
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
