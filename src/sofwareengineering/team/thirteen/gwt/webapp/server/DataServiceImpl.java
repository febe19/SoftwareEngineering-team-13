package sofwareengineering.team.thirteen.gwt.webapp.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import sofwareengineering.team.thirteen.gwt.webapp.client.DataService;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	private static final String DATA_FILE = "resources/GlobalLandTemperaturesByMajorCity_v1.csv";
	private static final String CSV_SEPARATOR = ",";
	
	public List<DataPoint> getData() {
        BufferedReader br = null;
        String line = "";
        
        ArrayList<String[]> dataSet = new ArrayList<String[]>();
        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        try {
            br = new BufferedReader(new FileReader(DATA_FILE));
            while ((line = br.readLine()) != null) {
            	dataSet.add(line.split(CSV_SEPARATOR));
            }

            for(int i=1;i<dataSet.size();i++){
            	int id=i;
            	int year = Integer.parseInt(((dataSet.get(i))[0]).substring(0, 4));
            	int month = Integer.parseInt(((dataSet.get(i))[0]).substring(5, 7));
            	String region =  ((dataSet.get(i))[3]);
            	String country = ((dataSet.get(i))[4]);
            	String latitude = ((dataSet.get(i))[5]);
            	String longitude = ((dataSet.get(i))[6]);
            	double averageTemperature = Double.parseDouble(dataSet.get(i)[1]);
            	double uncertainity = Double.parseDouble(dataSet.get(i)[2]);
            	
            	dataPoints.add(new DataPoint(id,year,month,region,country,
            			latitude,longitude,averageTemperature,uncertainity));
            	}
            
            
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
		return dataPoints;
	}

}
