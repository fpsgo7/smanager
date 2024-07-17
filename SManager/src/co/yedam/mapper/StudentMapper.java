package co.yedam.mapper;

import java.util.List;

import co.yedam.vo.StudentVO;

public interface StudentMapper {
	// 인터페이스를 완성 시키는 것은 studentMapper.xml 
	// 완성시킨다.
	
	List<StudentVO> studentList();
	
	// insert, update, delete => 변경 처리된 건수 반환값으로 지정
	// 5건 업데이트시 5 반환 , 1건 삭제시 1반환
	int insertStudent(StudentVO svo);
	
	int updateStudent(StudentVO svo);// 학생번호 기준으로 설정하자.
	
	int deleteStudent(String stdNo);
}
