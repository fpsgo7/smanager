package com.yedam.service;

import java.util.List;

import com.yedam.vo.MemberVO;
import com.yedam.vo.StudentVO;

public interface MemberService {
	MemberVO loginCheck(String id, String pw);

	List<MemberVO> getMembers(String orderBy, String responsibility);
	
	// 학생목록 조회
	List<StudentVO> studentList();
	
	// 학생 삭제
	boolean removeStudent(String sno);
	// 학생 추가
	boolean addStudent(StudentVO svo);
}
