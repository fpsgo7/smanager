package co.park.vo;

public class ReviewVO {
	private int id;
	private String memberId;
	private int restaurantId;
	private String menu;
	private int point;
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	private String reviewContent;
	private String placeFull;
	private String lastestDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getPlaceFull() {
		return placeFull;
	}
	public void setPlaceFull(String placeFull) {
		this.placeFull = placeFull;
	}
	public String getLastestDate() {
		return lastestDate;
	}
	public void setLastestDate(String lastestDate) {
		this.lastestDate = lastestDate;
	}
}
