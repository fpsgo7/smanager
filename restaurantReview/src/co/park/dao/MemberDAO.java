package co.park.dao;

import java.sql.SQLException;

import co.park.vo.MemberVO;

public class MemberDAO extends DAO{
//	// 테스트용
//	public static void main(String[] args) {
//		MemberDAO memberDAO = new MemberDAO();
//		System.out.println(memberDAO.idCheck("test1"));
//		
//	}
	
	/**
	 * 로그인 확인
	 * 성공시에는 객체를 
	 * 실패시에는 null을 반환한다.
	 */
	public MemberVO login(String id, String password) throws Exception {
		MemberVO memberVO = null;
		String sql = "SELECT id, password, grade"
				+ " FROM member"
				+ " where id = ? and password = ?"; 
		
		conn = getConn();
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
	}
	
	/**
	 * 아이디 유무 확인
	 * 아이디가 있으면 true반환 없으면 false반환
	 */
	public boolean idCheck(String id) throws Exception{
		String sql = "SELECT count(*)"
				+ " FROM member"
				+ " where id = ?"; 
		
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getInt("count(*)") == 1) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * 회원 가입
	 * 성공시 true 반환 실패시 false 반환
	 */
	public boolean join(String id, String password) throws Exception{
		String sql = "insert into member (id,password,grade)"
				+ "values( ? , ? ,1)";
		
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		int answer = pstmt.executeUpdate();
		if(answer == 1) {
			return true;
		}
		return false;
	}
}
