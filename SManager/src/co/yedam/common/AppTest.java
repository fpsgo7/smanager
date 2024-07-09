package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import co.yedam.vo.StudentVO;
/**
 * 테스트용이다.
 */
public class AppTest {
	// main 에 작성하면 복잡하니 따로 메서드로 꺼내자.
	public static Connection getConn() {
		/* DB 연결을 위한 값 작성 */
		// 오라클 db 연결 url 작성
		// @ip:포트번호:db이름
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";// 유저아이디
		String pass = "jsp";// 유저 비번
		// Connection 객체
		Connection conn = null;
		// DB를 연결하는 Connection 객체 가져오기
		try {
			Class.forName(driver);// 오라클 드라이버아 있는지 확인한다.
			conn = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return conn;
	}
	// 조회기능
	public static void search() {
		Connection conn = getConn();
		String sql = "select * "
				+ "from tbl_student";
		try {
			Statement stmt = conn.createStatement();
			// 해당 쿼리문을 실행한후 ResultSet 에 반환한다.
			ResultSet rs = stmt.executeQuery(sql);
			// ResultSet은 컬렉션 같은 형태이며
			// 반복자 접근이 가능하다.
			while(rs.next()) {// rs.next() 다음 값이 있으면 true를 반환한다.
				// "속성"값을 통하여 검색후 자료를 문자열로 가져온다.
				String address = rs.getString("address") != null ? rs.getString("address") : "";
				String birthDate = rs.getString("birth_date") != null ? rs.getString("birth_date") : "";
				System.out.println(rs.getString("std_no"));
				System.out.println(rs.getString("std_name"));
				System.out.println(rs.getString("std_phone"));
				System.out.println(address);
				System.out.println(birthDate);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 입력기능 
	public static void addStudent(String stdNo, String stdName, String stdPhone) {
		Connection conn = getConn();
		String sql = "insert into tbl_student(std_no, std_name, std_phone)"+
				"values('" + stdNo
				+ "', '"+stdName
				+ "', '"+stdPhone
				+ "')";
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // 처리된 건수를 반환
			System.out.println("처리된 건수는 " + r + "건입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 수정 기능
	public static void modStudent(StudentVO studentVO) {
		Connection conn = getConn();

		String sql = String.format("UPDATE tbl_student SET"
				+ " std_name = NVL('%s',std_name)"
				+ ", std_phone = NVL('%s',std_phone)"
				+ ", address = NVL('%s',address)"
				+ ",  birth_date =  NVL('%s',birth_date) "
				+ " WHERE std_no = '%s'"
				,studentVO.getStdName(),studentVO.getStdPhone(),studentVO.getAddress()
				,studentVO.getBirthDate(),studentVO.getStdNo());
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // 처리된 건수를 반환
			System.out.println("처리된 건수는 " + r + "건입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 삭제 기능
	public static void removeStudent(String stdNo) {
		Connection conn = getConn();
		if(stdNo == "") {
			System.out.println("아이디입력이 없습니다.");
		}else {
			stdNo = String.format("'%s'", stdNo);
		}

		String sql = String.format("Delete tbl_student"
				+ " where std_no = %s",stdNo);
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // 처리된 건수를 반환
			System.out.println("처리된 건수는 " + r + "건입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("학생번호 입력 > ");
		// 아무것도 입력하지 않으면 빈문자열 ""이 들어간다.
		String stdNo = scan.nextLine();
		System.out.print("학생이름 입력 > ");
		String stdName = scan.nextLine();
		System.out.print("연락처 입력 > ");
		String stdPhone = scan.nextLine();
		System.out.print("주소 입력 > ");
		String addresss = scan.nextLine();
		System.out.print("생일 입력 > ");
		String birthDate = scan.nextLine();
		// 업데이트 한후 확인하기
		StudentVO std = new StudentVO();
		std.setStdNo(stdNo);
		std.setStdName(stdName);
		std.setStdPhone(stdPhone);
		std.setAddress(addresss);
		std.setBirthDate(birthDate);
		modStudent(std);
		search();
		// 추가 삭제하기 
//		addStudent(stdNo,stdName,stdPhone);
//		search();
//		removeStudent(stdNo);
//		search();
	}

}
