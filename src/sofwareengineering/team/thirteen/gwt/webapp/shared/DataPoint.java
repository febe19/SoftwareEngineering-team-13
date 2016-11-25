package sofwareengineering.team.thirteen.gwt.webapp.shared;

import java.sql.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataPoint implements IsSerializable{
	
	private int id;
	private int year;
	private int month;
	
	private String region;
	private String country;
	private String latitude;
	private String longitude;
	private double averageTemperature;
	private double uncertainity;
	private Date date;
	
/*Empty constructor needed for serialization*/
public DataPoint(){}

public DataPoint(int id, int year, int month,String region, String country, String latitude, String longitude, double averageTemperature,
		double uncertainity) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.setRegion(region);
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.averageTemperature = averageTemperature;
		this.uncertainity = uncertainity;
	}

	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
	this.month = month;
	}
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCity(String city) {
		this.region= city;
	}
	public String getCity(){
		return region;
	}
}