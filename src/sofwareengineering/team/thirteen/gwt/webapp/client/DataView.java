package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public abstract class DataView extends Composite{
	private List<DataPoint> data;
	private List<DataPoint> TableData;
	private List<DataPoint> countryList;
	private List<DataPoint> cityList;
	
	
	public List<DataPoint> getCountryList2() {
		return countryList;
	}

	public void setCountryList(List<DataPoint> countryList) {
		this.countryList = countryList;
	}

	public List<DataPoint> getTableData() {
		return TableData;
	}

	public void setTableData(List<DataPoint> tabledata) {
		TableData = tabledata;
	}

	private DataServiceAsync dataService = GWT.create(DataService.class);
	
	public abstract void fetchData();
	public abstract void fetchCountryList();
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

	public List<DataPoint> getCityList2() {
		return cityList;
	}

	public void setCityList(List<DataPoint> cityList) {
		this.cityList = cityList;
	}
}
