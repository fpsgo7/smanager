package co.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.mapper.DataSource;
import co.yedam.mapper.StudentMapper;
import co.yedam.vo.StudentVO;

public class MybatisTest {
	
	public static void main(String[] args) {
		SqlSessionFactory factory = 
			DataSource.getInstance(); // SqlFactory 얻어오기
		SqlSession sqlSession = 
			factory.openSession(true); // SqlSession 객체를 얻는 방법
		int cnt =0;
		// 매퍼 인터페이스 구현 = 구현 클래스
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		// insert 해보기
		StudentVO student = new StudentVO();
		student.setStdNo("S2024-08");
		student.setStdName("새로운");
		student.setStdPhone("010-1212-9999"); 
		
		cnt = mapper.insertStudent(student);
		System.out.println("처리된 건수" + cnt);
		sqlSession.commit(); // openSession 의 매개값으로 true를 작성하면 자동 커밋이된다.
		
		// update 해보기
		cnt = mapper.updateStudent(student);
		System.out.println("처리된 건수" + cnt);
		
		// delete 해보기
		cnt = mapper.deleteStudent("S2024-08");
		System.out.println("처리된 건수" + cnt);
		
		// 전체 조회해보기 
		List<StudentVO> list = mapper.studentList();
		for(StudentVO svo : list ) {
			System.out.println(svo);
		}
	}

}
