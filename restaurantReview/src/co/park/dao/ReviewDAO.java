package co.park.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.park.vo.RestaurantVO;
import co.park.vo.ReviewVO;


public class ReviewDAO extends DAO{
	// 테스트용
	public static void main(String[] args) {
		ReviewDAO dao = new ReviewDAO();
//		for (ReviewVO vo : dao.getReviews(1)) {
//			System.out.println(vo.getMenu());
//		}
		
		ReviewVO vo = new ReviewVO();
		vo.setMemberId("test1");
		vo.setRestaurantId(1);
		vo.setMenu("테스트");
		vo.setPoint(3);
		vo.setReviewContent("123");
		vo.setPlaceFull("상");
		System.out.println(dao.insertReview(vo));
		
		
	}
	
	public List<ReviewVO> getReviewsByRestaurant(int restaurantId ){
		List<ReviewVO> list = new ArrayList<ReviewVO>();
		String sql = "SELECT id, member_id ,restaurant_id, "
				+ " menu,"
				+ " point,"
				+ " review_content,"
				+ " place_full,"
				+ " lastest_date"
				+ " FROM review"
				+ " WHERE restaurant_id = ? ";
		conn = getConn();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertReview(ReviewVO vo){
		
		String sql = "INSERT INTO review ( id, "
				+ "member_id, "
				+ "restaurant_id,"
				+ "menu,"
				+ "point, "
				+ "review_content, "
				+ "place_full )"
				+ "values(review_id_seq.NEXTVAL, ? , ? , ? , ? , ?,?)";
		conn = getConn();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
