package co.park.dao;

import java.util.ArrayList;
import java.util.List;

import co.park.vo.RestaurantVO;

public class RestaurantDAO extends DAO{
	// 테스트용
//	public static void main(String[] args) {
//		RestaurantDAO dao = new RestaurantDAO();
//		for (RestaurantVO vo : dao.getRestaurants()) {
//			System.out.println(vo.getName());
//		}
//		
////		RestaurantVO vo = new RestaurantVO();
////		vo.setName("1");
////		vo.setLowestPrice(1000);
////		vo.setHighestPrice(10000);
////		vo.setDurationOfTime(10);
////		System.out.println(dao.insertRestaurant(vo));
//		
////		RestaurantVO vo = new RestaurantVO();
////		vo.setId(4);
//////		vo.setName("new");
//////		vo.setLowestPrice(5200);
//////		vo.setHighestPrice(600);
////		vo.setDurationOfTime(1);
////		System.out.println(dao.updateRestaurant(vo));
//		
//		System.out.println(dao.deleteRestaurant(4));
//	}
	
	public List<RestaurantVO> getRestaurants() throws Exception{
		List<RestaurantVO> list = new ArrayList<RestaurantVO>();
		String sql = "SELECT id, name,lowest_price, "
				+ " highest_price,"
				+ " duration_of_time, lastest_date"
				+ " FROM restaurant";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			RestaurantVO vo = new RestaurantVO();
			vo.setId(rs.getInt("id"));
			vo.setName(rs.getString("name"));
			vo.setLowestPrice(rs.getInt("lowest_price"));
			vo.setHighestPrice(rs.getInt("highest_price"));
			vo.setDurationOfTime(rs.getInt("duration_of_time"));
			vo.setLastestDate(rs.getString("lastest_date"));
			list.add(vo);
		}
		return list;
	}
	
	public boolean insertRestaurant(RestaurantVO restaurantVO) throws Exception{
		
		String sql = "INSERT INTO restaurant "
				+ "(id ,name, lowest_price, "
				+ "highest_price, duration_of_time)"
				+ "values( restaurant_id_seq.NEXTVAL ,?,?,?,?)";
		conn = getConn();
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, restaurantVO.getName());
		pstmt.setLong(2, restaurantVO.getLowestPrice());
		pstmt.setLong(3, restaurantVO.getHighestPrice());
		pstmt.setLong(4, restaurantVO.getDurationOfTime());
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateRestaurant(RestaurantVO restaurantVO) throws Exception{

		String sql = "UPDATE restaurant"
				+ " SET"
				+ " name = NVL(?,name),"
				+ " lowest_price = CASE WHEN ? <= 0 THEN lowest_price"
				+ "					ELSE ? END,"
				+ " highest_price = CASE WHEN ? <= 0 THEN highest_price"
				+ "					ELSE ? END,"
				+ " duration_of_time = CASE WHEN ? <= 0 THEN duration_of_time"
				+ "					ELSE ? END,"
				+ " lastest_date = SYSDATE"
				+ " WHERE id = ?";
		conn = getConn();
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, restaurantVO.getName());
		pstmt.setLong(2, restaurantVO.getLowestPrice());
		pstmt.setLong(3, restaurantVO.getLowestPrice());
		pstmt.setLong(4, restaurantVO.getHighestPrice());
		pstmt.setLong(5, restaurantVO.getHighestPrice());
		pstmt.setLong(6, restaurantVO.getDurationOfTime());
		pstmt.setLong(7, restaurantVO.getDurationOfTime());
		pstmt.setLong(8, restaurantVO.getId());
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteRestaurant(int id) throws Exception{
		String sql = "DELETE restaurant WHERE id = ?";
		conn = getConn();
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		if(pstmt.executeUpdate() == 1) {
			return true;
		}
		return false;
	}
}
