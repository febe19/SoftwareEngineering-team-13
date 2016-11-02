package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataSet implements Filter {

	private static int DataPointId = 0;
    private ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
	
    public static void csvParser() {

    	String csvFile = "https://github.com/febe19/SoftwareEngineering-team-13/blob/dev/src/sofwareengineering/team/thirteen/gwt/webapp/client/GlobalLandTemperaturesByMajorCity_v1.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String[]> dataSet = new ArrayList<String[]>();
        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                
            	dataSet.add(line.split(cvsSplitBy));

                

            }

            for(int i=1;i<dataSet.size();i++){
            	int id=DataPointId++;
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

    }


	@Override
	public void ascendingOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void descendingOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByCounty(ArrayList<String> countries) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByYear(int year) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByYear(int start, int end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByUncertainity(double lowerBound, double upperBound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByCity(ArrayList<String> cities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByLatitude(String latitude) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByLongitude(String longitude) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortByAverageTemperature(double lowerBound, double upperBound) {
		// TODO Auto-generated method stub
		
	}
}
