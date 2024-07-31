package com.yedam.service;

import java.util.List;
import java.util.Map;

import com.yedam.vo.MemberVO;
import com.yedam.vo.StudentVO;

public interface MemberService {
	/*맴버 파트*/
	MemberVO loginCheck(String id, String pw);

	List<MemberVO> getMembers(String orderBy, String responsibility);
	
	// 차트(작성자별 건수)
	List<Map<String,Object>> getCountByMember();
	
	/*학생 파트*/
	// 학생목록 조회
	List<StudentVO> studentList();
	
	// 학생 삭제
	boolean removeStudent(String sno);
	// 학생 추가
	boolean addStudent(StudentVO svo);
}
