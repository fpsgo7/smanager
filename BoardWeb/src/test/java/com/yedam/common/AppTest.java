package com.yedam.common;

import org.apache.ibatis.session.SqlSession;

import com.yedam.mapper.StudentMapper;

public class AppTest {
	public static void main(String[] args) {
		// 팩토리에서 세션을 한번에 가져왔다.
		SqlSession sqlSession = 
				DataSource.getInstance().openSession();
		
		StudentMapper mapper 
			= sqlSession.getMapper(StudentMapper.class);
		mapper.studentList().forEach(
				student -> {
					System.out.println(student);
					System.out.println(student.toString());
				});
		
	}
	
	
}
