package co.park.dao;

import java.sql.SQLException;

import co.park.vo.MemberVO;

public class MemberDAO extends DAO{
	// 테스트용
	public static void main(String[] args) {
		MemberDAO memberDAO = new MemberDAO();
		System.out.println(memberDAO.idCheck("test1"));
		
	}
	
	/**
	 * 로그인 확인
	 * 성공시에는 객체를 
	 * 실패시에는 null을 반환한다.
	 */
	public MemberVO login(String id, String password) {
		MemberVO memberVO = null;
		String sql = "SELECT id, password, grade"
				+ " FROM member"
				+ " where id = ? and password = ?"; 
		
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setGrade(rs.getInt("grade"));
			}
			return memberVO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 아이디 유무 확인
	 * 아이디가 있으면 1반환 없으면 0반환
	 */
	public int idCheck(String id) {
		String sql = "SELECT count(*)"
				+ " FROM member"
				+ " where id = ?"; 
		
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("count(*)");
			}
			return 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 회원 가입
	 */
	public boolean join(String id, String password) {
		String sql = "insert into member (id,password,grade)"
				+ "values( ? , ? ,1)";
		
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			int answer = pstmt.executeUpdate();
			if(answer == 1) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
