package co.park.vo;

public class RestaurantVO {
	private int id;
	private String name;
	private int lowestPrice; 
	private int highestPrice; 
	private int durationOfTime;
	private String lastestDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(int lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public int getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(int highestPrice) {
		this.highestPrice = highestPrice;
	}
	public int getDurationOfTime() {
		return durationOfTime;
	}
	public void setDurationOfTime(int durationOfTime) {
		this.durationOfTime = durationOfTime;
	}
	public String getLastestDate() {
		return lastestDate;
	}
	public void setLastestDate(String lastestDate) {
		this.lastestDate = lastestDate;
	}
	
	
}
