package sofwareengineering.team.thirteen.gwt.webapp.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import sofwareengineering.team.thirteen.gwt.webapp.client.DataService;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	
	private static final String DATA_FILE = "resources/GlobalLandTemperaturesByMajorCity_v1.csv";
	private static final String CSV_SEPARATOR = ",";
	
	//Login for dataBank
	private static final String PROD_PASSWORD = "1234";
	private static final String PROD_URL="jdbc:google:mysql://softwareengineeringteam13:europe-west1:se13/se13";
	private static final String PROD_USER="root";
	
	private static final String DEV_USER="paedi";
	private static final String DEV_PASSWORD="AY0nVCAmYDL331og";
	private static final String DEV_URL="jdbc:mysql://paedi.icu.uzh.ch:8080/paedi";
	
	private String testDriver = "com.mysql.jdbc.Driver";
    private String prodDriver = "com.mysql.jdbc.GoogleDriver";
    public DataServiceImpl(){}

    public Connection getConnection(){
        
        Connection conn = null;

        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

            try {
                Class.forName(prodDriver); 
                conn = DriverManager.getConnection(PROD_URL,PROD_USER,PROD_PASSWORD );
            }catch(Exception e){
                e.printStackTrace();
            }

        } else {
        	
            try {
                Class.forName(testDriver);
                conn = DriverManager.getConnection(DEV_URL,DEV_USER,DEV_PASSWORD);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return conn;

    }
    
    
    //Method gets the data from the DB and return it in form of an arrayList. 
	public ArrayList<DataPoint> getData() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList();
		
		
		try {
			statement = connection.prepareStatement("SELECT * FROM `temperature-data`");
			result = statement.executeQuery();
			
			while (result.next()) {
				
				double temp = result.getDouble("Temperature");
				String city = result.getString("City");
				String country = result.getString("Country");
				DataPoint p = new DataPoint();
				p.setAverageTemperature(temp);
				p.setRegion(city);
				p.setCountry(country);

				data.add(p);		
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public ArrayList<DataPoint> getMapData(int year,double minTemperature, double maxTemperature,
			double uncertainity, String selectedCity, String selectedCountry) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList();
		
		String query = 
				"SELECT YEAR(Date), City, AVG(Temperature) AS t FROM `temperature-data` "
						+ "WHERE YEAR(DATE) = ? AND Temperature >="+minTemperature+" AND Temperature <="+maxTemperature
						+" AND Uncertainty <=" + uncertainity+" AND City="+selectedCity+" AND Country="+selectedCountry
						+ " GROUP BY City, YEAR(DATE)";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, year);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				double temp = result.getDouble("t");
				String city = result.getString("City");
				int yearPoint = year;
				
				DataPoint p = new DataPoint();
				p.setAverageTemperature(temp);
				p.setRegion(city);
				p.setYear(year);
				
				data.add(p);	
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public ArrayList<DataPoint> getTableData(int year) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList<>();
		
		String query = 
				"SELECT * "
				+"FROM `temperature-data` "
				+ "WHERE Year(Date) ="+ year;
		
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				
				String country = result.getString("Country");
				String city = result.getString("City");
				double temp = result.getDouble("Temperature");
				double uncertainity = result.getDouble("Uncertainty");
				Date date = result.getDate("Date");
				DataPoint p = new DataPoint();
				p.setCountry(country);
				p.setRegion(city);
				p.setAverageTemperature(temp);
				p.setUncertainity(uncertainity);
				p.setDate(date);
				
				data.add(p);		
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
