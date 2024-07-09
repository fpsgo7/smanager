package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

/**
 * 목록(SELECT), 등록(create), 수정(UPDATE), 삭제(delete)
 * 주의 : DAO 메시지가 없다. 콘솔 출력같은것을 구현하지 말것
 */
public class StudentDAO extends DAO{

	/**
	 * tbl_student 의 목록을 반환한다
	 * @return tbl_student 테이블의 리스트
	 */
	public List<StudentVO> selectList(){
		List<StudentVO> list = new ArrayList<StudentVO>();
		String sql = "select * from tbl_student order by std_no";
		
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				StudentVO svo = new StudentVO();
				svo.setAddress(rs.getString("address"));
				svo.setBirthDate(rs.getString("birth_date"));
				svo.setCreationDate(rs.getDate("creation_date"));
				svo.setStdName(rs.getString("std_name"));
				svo.setStdNo(rs.getString("std_no"));
				svo.setStdPhone(rs.getString("std_phone"));
				list.add(svo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 등록기능
	 */
	public boolean insertStudent(StudentVO studentVO) {
		
		String sql ="insert into tbl_student(std_no, std_name, std_phone,address,birth_date)"
				+"values(?,?,?,?,?)";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentVO.getStdNo());
			pstmt.setString(2, studentVO.getStdName());
			pstmt.setString(3, studentVO.getStdPhone());
			pstmt.setString(4, studentVO.getAddress());
			pstmt.setString(5, studentVO.getBirthDate());
			
			int r = pstmt.executeUpdate();
			if(r == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 수정기능
	 */
	public boolean modifyStudent(StudentVO studentVO) {
		
		String sql ="UPDATE tbl_student SET"
			+ " std_name = NVL('?',std_name),"
			+ " std_phone = NVL('?',std_phone),"
			+ " address = NVL('?',address),"
			+ " birth_date =  NVL('?',birth_date)"
			+ " WHERE std_no = '?'";
		
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentVO.getStdName());
			pstmt.setString(2, studentVO.getStdPhone());
			pstmt.setString(3, studentVO.getAddress());
			pstmt.setString(4, studentVO.getBirthDate());
			pstmt.setString(5, studentVO.getStdNo());
			int r = pstmt.executeUpdate();
			if(r == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 삭제 기능
	 */
	public boolean deleteStudent(String stdNo) {
		
		String sql ="Delete tbl_student"
				+ " where std_no = ?";
		
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stdNo);

			int r = pstmt.executeUpdate();
			if(r == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
