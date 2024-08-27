package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.MemberVO;
import com.yedam.vo.StudentVO;

public interface StudentMapper {
	// 구현은 studentMapper.xml 에서 한다.
	public List<StudentVO> studentList();
	
	public StudentVO student(String stdNo);
	
	public int insertStudent(StudentVO vo);
	
	public int deleteStudent(String stdNo);
	
	/*맴버 관련*/
	// 로그인 체크 (매개변수에 파라미터 대응시키기)
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);

	public List<MemberVO> selectMembers(@Param("orderBy") String orderBY, @Param("responsibility") String responsibility);
	
	// 차트 (작성자별 건수)
	List<Map<String,Object>> selectCountByMember();
	
	// 회원 탈퇴
	String deleteMemberProc(Map<String, String> map);
	
}
