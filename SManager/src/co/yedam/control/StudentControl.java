package co.yedam.control;

import java.util.List;
import java.util.Scanner;

import co.yedam.dao.StudentDAO;
import co.yedam.vo.StudentVO;

/**
 * 사용자 입력 가이드, 처리된 결과를 출력
 */
public class StudentControl {
	Scanner scan = new Scanner(System.in);
	StudentDAO sdao = new StudentDAO();
	public void run() {
		boolean isTrue  = true;
		
		while(isTrue) {
			System.out.println("1.학생목록 2.등록 3.수정 4.삭제 5.종료");
			System.out.print("선택> ");
			int menu = Integer.parseInt(scan.nextLine());
			
			switch (menu) {
			case 1:
				studentList();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				modifyStudent();
				break;
			case 4:
				removeStudent();
				break;
			case 5:
				System.out.println("종료합니다.");
				isTrue = false;
				break;
			}
		}
	}
	
	// 목록 출력 기능 메서드
	void studentList() {
		List<StudentVO> students = sdao.selectList();
		System.out.println("학생번호 학생이름 연락처");
		System.out.println("---------------------------");
		for (StudentVO studentVO : students) {
			System.out.println(studentVO.briefShow());
		}
	}

	// 등록 기능
	void addStudent() {
		System.out.print("학생번호 입력 > ");
		String stdNo = scan.nextLine();
		System.out.print("학생이름 입력 > ");
		String stdName = scan.nextLine();
		System.out.print("연락처 입력 > ");
		String stdPhone = scan.nextLine();
		System.out.print("주소 입력 > ");
		String addresss = scan.nextLine();
		System.out.print("생일 입력 > ");
		String birthDate = scan.nextLine();
		
		StudentVO std = new StudentVO();
		std.setStdNo(stdNo);
		std.setStdName(stdName);
		std.setStdPhone(stdPhone);
		std.setAddress(addresss);
		std.setBirthDate(birthDate);
		if(sdao.insertStudent(std)) {
			System.out.println("저장완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}
	}

	// 수정 기능
	void modifyStudent() {
		int count =0;
		String stdNo = null;
		while(true) {
			System.out.print("변경할 학생번호 > ");
			stdNo = scan.nextLine();
			if(sdao.selectStudentByNo(stdNo) == 1) {
				break;
			}
			System.out.println("학생번호에 대한 학생정보가 없습니다.");
			count ++;
			if(count >3) {
				return;
			}
		}
		
		System.out.print("변경할 학생이름 > ");
		String stdName = scan.nextLine();
		System.out.print("변경할 연락처 > ");
		String stdPhone = scan.nextLine();
		System.out.print("변경할 주소 > ");
		String addresss = scan.nextLine();
		
		StudentVO std = new StudentVO();
		std.setStdNo(stdNo);
		std.setStdName(stdName);
		std.setStdPhone(stdPhone);
		std.setAddress(addresss);
		if(sdao.modifyStudent(std)) {
			System.out.println("수정완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}
	}
	// 삭제기능
	void removeStudent(){
		System.out.print("학생번호 입력 > ");
		String stdNo = scan.nextLine();

		if(sdao.deleteStudent(stdNo)) {
			System.out.println("삭제완료!");
		} else {
			System.out.println("처리중 예외발생!");
		}
	}
}
