package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.StudentMapper;
import com.yedam.vo.MemberVO;
import com.yedam.vo.StudentVO;

public class MemberServiceImpl implements MemberService{
	SqlSession sqlSession = 
			DataSource.getInstance().openSession(true);
	StudentMapper mapper 
		= sqlSession.getMapper(StudentMapper.class);
	
	@Override
	public MemberVO loginCheck(String id, String pw) {
		return mapper.selectMember(id, pw);
	}

	@Override
	public List<MemberVO> getMembers(String orderBy, String responsibility) {
		return mapper.selectMembers(orderBy, responsibility);
	}
	
	@Override
	public List<Map<String, Object>> getCountByMember() {
		
		return mapper.selectCountByMember();
	}
	// 회원 탈퇴
	@Override
	public String signOutProc(Map<String, String> map) {
		return mapper.deleteMemberProc(map);
	}

	
	/*학생 파트*/
	@Override
	public List<StudentVO> studentList() {
		
		return mapper.studentList();
	}

	@Override
	public boolean removeStudent(String sno) {
		return mapper.deleteStudent(sno) == 1;
	}

	@Override
	public boolean addStudent(StudentVO svo) {
		return mapper.insertStudent(svo) == 1;
	}




}
