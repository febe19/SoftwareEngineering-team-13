package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public abstract class DataView extends Composite{
	private List<DataPoint> data;
	private DataServiceAsync dataService = GWT.create(DataService.class);
	
	public abstract void fetchData();

	public DataServiceAsync getDataService() {
		return dataService;
	}

	public void setDataService(DataServiceAsync dataService) {
		this.dataService = dataService;
	}

	public List<DataPoint> getData() {
		return data;
	}

	public void setData(List<DataPoint> data) {
		this.data = data;
	}
}
