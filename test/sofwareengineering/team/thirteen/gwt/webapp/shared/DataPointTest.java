package sofwareengineering.team.thirteen.gwt.webapp.shared;

import static org.junit.Assert.*;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class DataPointTest extends GWTTestCase {

	
	public void testDataPoint(){
		DataPoint dataPointTest = new DataPoint();
		dataPointTest.setAverageTemperature(23.5);
		dataPointTest.setCountry("Switzerland");
		dataPointTest.setRegion("Zurich");
		dataPointTest.setYear(2016);
		
		assertEquals(23.5,dataPointTest.getAverageTemperature());
		assertEquals("Switzerland",dataPointTest.getCountry());
		assertEquals("Zurich",dataPointTest.getRegion());
		assertEquals(2016,dataPointTest.getYear());
	}
	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}


}
