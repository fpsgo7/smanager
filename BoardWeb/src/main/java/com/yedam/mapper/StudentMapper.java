package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.StudentVO;

public interface StudentMapper {
	// 구현은 studentMapper.xml 에서 한다.
	public List<StudentVO> studentList();
	
	public StudentVO student(String stdNo);
	
	public int insertStudent(StudentVO vo);
}
