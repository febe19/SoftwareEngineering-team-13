package sofwareengineering.team.thirteen.gwt.webapp.server;

import com.google.gwt.junit.client.GWTTestCase;
import static org.junit.Assert.*;
import sofwareengineering.team.thirteen.gwt.webapp.server.DataServiceImpl;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

import org.junit.Test;
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

public class DataServiceImplTest extends GWTTestCase {
	DataServiceImpl dataTest = new DataServiceImpl();
	
	public void testConnection(){
		Connection connTest = dataTest.getConnection();
		assertNotNull(connTest);
	}
	
	public void testGetData(){
		ArrayList<DataPoint> ArrayDataTest = dataTest.getData();
		int sizeTest = ArrayDataTest.size();
		DataPoint pointTest = ArrayDataTest.get(1);
		
		assertTrue(pointTest.getCountry().equals("United States"));
		assertEquals("Chicago",pointTest.getRegion());
		assertEquals(5.436,pointTest.getAverageTemperature());
		assertEquals(91680,sizeTest);
	}
	
	public void testGetMapData(){
		ArrayList<DataPoint> ArrayMapDataTest = dataTest.getMapData(2011);
		
		assertEquals("Abidjan",ArrayMapDataTest.get(0).getRegion());
		assertEquals(2011, ArrayMapDataTest.get(0).getYear());
		
	}
	
	public void testGetTabledata(){
		ArrayList<DataPoint> ArrayTableDataTest = dataTest.getTableData();
		DataPoint pointTest = ArrayTableDataTest.get(0);
		assertEquals("Abidjan",pointTest.getRegion());
		assertEquals(27.36, pointTest.getAverageTemperature());
	}
	

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}


}
