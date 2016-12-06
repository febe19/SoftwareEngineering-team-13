package sofwareengineering.team.thirteen.gwt.webapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import sofwareengineering.team.thirteen.gwt.webapp.client.DataService;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	
	//Login for dataBank when deployed to the App engine
	private static final String PROD_PASSWORD = "1234";
	private static final String PROD_URL="jdbc:google:mysql://softwareengineeringteam13:europe-west1:se13/se13";
	private static final String PROD_USER="root";
	
	//Login for dataBank when NOT deployed on App engine.
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
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		
		
		try {
			statement = connection.prepareStatement("SELECT * FROM `temperature-data`");
			result = statement.executeQuery();
			
			while (result.next()) {
				DataPoint p = new DataPoint();
				p.setAverageTemperature(result.getDouble("Temperature"));
				p.setRegion(result.getString("City"));
				p.setCountry(result.getString("Country"));
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
						+ "WHERE YEAR(DATE) ="+year+" "
						+ "AND Temperature >="+minTemperature+" "
						+ "AND Temperature <="+maxTemperature+" "
						+ "AND Uncertainty <=" + uncertainity+" "
						+ "AND City="+selectedCity+" "
						+ "AND Country="+selectedCountry+" "
						+ "GROUP BY City, YEAR(DATE)";
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				DataPoint p = new DataPoint();
				p.setAverageTemperature(result.getDouble("t"));
				p.setRegion(result.getString("City"));
				p.setYear(year);
				data.add(p);	
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public ArrayList<DataPoint> getTableData(int year,double minTemperature, double maxTemperature,double uncertainty,String city, String country) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList<>();
		String query = 
				"SELECT * "
				+"FROM `temperature-data` "
				+ "WHERE Year(Date) ="+ year
				+" AND Uncertainty<="+ uncertainty
				+" AND Temperature >="+ minTemperature
				+" AND Temperature <="+maxTemperature
				+" AND City ="+city
				+" AND Country ="+country
				+" AND Lat =Lat"
				+" AND Lon=Lon";
		
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				DataPoint p = new DataPoint();
				p.setCountry(result.getString("Country"));
				p.setRegion(result.getString("City"));
				p.setAverageTemperature(result.getDouble("Temperature"));
				p.setUncertainity(result.getDouble("Uncertainty"));
				p.setDate(result.getDate("Date"));
				p.setLatitude(String.valueOf(result.getDouble("Lat")));
				p.setLongitude(String.valueOf(result.getDouble("Lon")));
				data.add(p);		
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public ArrayList<DataPoint> getCountryList() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList<>();
		
		String query = "SELECT DISTINCT COUNTRY FROM `temperature-data` ORDER BY (COUNTRY)";
		
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				DataPoint p = new DataPoint();
				p.setCountry(result.getString("Country"));
				data.add(p);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public ArrayList<DataPoint> getCityList(String country) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet result;
		
		ArrayList<DataPoint> data = new ArrayList<>();
		
		String query = "SELECT DISTINCT CITY FROM `temperature-data` WHERE Country="+country+" ORDER BY (CITY)";
		
		try {
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();
			
			while (result.next()) {
				DataPoint p = new DataPoint();
				p.setCity(result.getString("City"));
				data.add(p);
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
