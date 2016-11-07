package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class DataSet implements Filter {
	private DataServiceAsync dataService = GWT.create(DataService.class);
	private List<DataPoint> data;
	
    public void getData() {
    	if (dataService == null) {
    	      dataService = GWT.create(DataService.class);
    	    }

    	    AsyncCallback<List<DataPoint>> callback = new AsyncCallback<List<DataPoint>>() {

			@Override
			public void onSuccess(List<DataPoint> result) {
				data=result;
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				
			}
			
    	    };

    	    dataService.getData(callback);
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
