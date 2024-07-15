package co.park.dao;

import java.util.ArrayList;
import java.util.List;

import co.park.vo.ReviewVO;


public class ReviewDAO extends DAO{
	// 테스트용
//	public static void main(String[] args) {
//		ReviewDAO dao = new ReviewDAO();
////		for (ReviewVO vo : dao.getReviews(1)) {
////			System.out.println(vo.getMenu());
////		}
//		
//		ReviewVO vo = new ReviewVO();
//		vo.setMenu("새로운");
////		vo.setPoint(5);
//		vo.setReviewContent("123");
////		vo.setPlaceFull("상");
//		vo.setId(17);
//		System.out.println(dao.updateReview(vo));
//		
//		
//	}
	
	public List<ReviewVO> getReviewsByRestaurant(int restaurantId ) throws Exception{
		List<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "SELECT id, member_id ,restaurant_id, "
				+ " menu,"
				+ " point,"
				+ " review_content,"
				+ " place_full,"
				+ " lastest_date"
				+ " FROM review"
				+ " WHERE restaurant_id = ? "
				+ " ORDER BY id";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1 , restaurantId);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			ReviewVO vo = new ReviewVO();
			vo.setId(rs.getInt("id"));
			vo.setMemberId(rs.getString("member_id"));
			vo.setRestaurantId(rs.getInt("restaurant_id"));
			vo.setMenu(rs.getString("menu"));
			vo.setPoint(rs.getInt("point"));
			vo.setReviewContent(rs.getString("review_content"));
			vo.setPlaceFull(rs.getString("place_full"));
			vo.setLastestDate(rs.getString("lastest_date"));
			list.add(vo);
		}
		return list;
	}
	
	public List<ReviewVO> getReviewsByRestaurant(int restaurantId , String memberId ) throws Exception{
		List<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "SELECT id, member_id ,restaurant_id, "
				+ " menu,"
				+ " point,"
				+ " review_content,"
				+ " place_full,"
				+ " lastest_date"
				+ " FROM review"
				+ " WHERE restaurant_id = ? "
				+ " AND member_id = ?"
				+ " ORDER BY id";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1 , restaurantId);
		pstmt.setString(2 , memberId);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			ReviewVO vo = new ReviewVO();
			vo.setId(rs.getInt("id"));
			vo.setMemberId(rs.getString("member_id"));
			vo.setRestaurantId(rs.getInt("restaurant_id"));
			vo.setMenu(rs.getString("menu"));
			vo.setPoint(rs.getInt("point"));
			vo.setReviewContent(rs.getString("review_content"));
			vo.setPlaceFull(rs.getString("place_full"));
			vo.setLastestDate(rs.getString("lastest_date"));
			list.add(vo);
		}
		return list;
	}
	
	public boolean insertReview(ReviewVO vo) throws Exception{
		
		String sql = "INSERT INTO review ( id, "
				+ "member_id, "
				+ "restaurant_id,"
				+ "menu,"
				+ "point, "
				+ "review_content, "
				+ "place_full )"
				+ "values(review_id_seq.NEXTVAL, ? , ? , ? , ? , ?,?)";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setLong(2, vo.getRestaurantId());
		pstmt.setString(3, vo.getMenu());
		pstmt.setLong(4, vo.getPoint());
		pstmt.setString(5, vo.getReviewContent());
		pstmt.setString(6, vo.getPlaceFull());
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateReview(ReviewVO vo) throws Exception{

		String sql = "UPDATE review"
				+ " SET"
				+ " menu = NVL(?,menu),"
				+ " point = CASE WHEN ? <= 0 THEN point"
				+ "					ELSE ? END,"
				+ " review_content = NVL(?,review_content),"
				+ " place_full = NVL(?,place_full),"
				+ " lastest_date = SYSDATE"
				+ " WHERE id = ?";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMenu());
		pstmt.setLong(2, vo.getPoint());
		pstmt.setLong(3, vo.getPoint());
		pstmt.setString(4, vo.getReviewContent());
		pstmt.setString(5, vo.getPlaceFull());
		pstmt.setLong(6, vo.getId());
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteReview(int id) throws Exception{
		String sql = "DELETE review"
				+ " WHERE id = ?";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
}
