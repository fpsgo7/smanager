package com.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.StudentVO;

public interface StudentMapper {
	// 구현은 studentMapper.xml 에서 한다.
	public List<StudentVO> studentList();
	
	public StudentVO student(String stdNo);
	
	public int insertStudent(StudentVO vo);
	
	// 로그인 체크 (매개변수에 파라미터 대응시키기)
	int selectMember(@Param("id") String id, @Param("pw") String pw);
}
