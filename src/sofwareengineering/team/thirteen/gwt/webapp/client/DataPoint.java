package sofwareengineering.team.thirteen.gwt.webapp.client;

public class DataPoint {
	private int id;
	private int year;
	private String country;
	private String latitude;
	private String longitude;
	private double averageTemperature;
	private double uncertainity;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public double getAverageTemperature() {
		return averageTemperature;
	}
	public void setAverageTemperature(double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	public double getUncertainity() {
		return uncertainity;
	}
	public void setUncertainity(double uncertainity) {
		this.uncertainity = uncertainity;
	}
}
