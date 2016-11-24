package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class MapView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;
	
	//default attributes that will be changed with the Sliders/Boxes using getters and setters
	private int currentYear=2013;


	private double maxTemperature=40;
	private double minTemperature=-30;
	private double uncertainity=15;
	//all the cities and countries will be shown
	private String city="city";
	private String country="country";
	private Label yearLabel = new Label(""+currentYear);

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getUncertainity() {
		return uncertainity;
	}

	public void setUncertainity(double uncertainity) {
		this.uncertainity = uncertainity;
	}

	// Create the MapView
	public MapView() {
		initWidget(mainPanel);
		initContent();
	}

	public double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}


	private void initContent() {
		yearLabel.addStyleName("gwt-YearLabel");
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				mainPanel.addWest(geoChart, 1200);
				mainPanel.addEast(yearLabel, 200);
				fetchData();
			}
		});
	}

	// Prepare the data which is shown
	//The data will come in form of an ArrayList and can be accessed with getData().get().getRegion()
	private void draw() {
		if (getData() != null) {

			DataTable dataTable = DataTable.create();
			dataTable.addColumn(ColumnType.STRING, "City");
			dataTable.addColumn(ColumnType.NUMBER, "Temperature");

			dataTable.addRows(getData().size());
			for (int i = 0; i < getData().size(); i++) {

				dataTable.setValue(i, 0, getData().get(i).getRegion());
				dataTable.setValue(i, 1, getData().get(i).getAverageTemperature());

			}
			yearLabel.setText(""+currentYear);
			geoChart.draw(dataTable, getGeoChartOptions());
		}
	}

	
	// Set Style Option for Geochart TODO: Size of the markers
	private GeoChartOptions getGeoChartOptions() {
		GeoChartOptions options = GeoChartOptions.create();
		options.setDisplayMode(DisplayMode.MARKERS);
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors("0000FF", "5858FA", "A9A9F5", "F7819F","FE2E64", "FF0040");
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("#858585");
		return options;

	}

	// Method which asks for data from the DataServiceImpl class and then start
	// the drawing of the map
	public void setCurrentYear(int year){
		currentYear=year;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				draw();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
			}
		};

		// call to server with the correct attributes (default at the beginning)
		getDataService().getMapData(currentYear,minTemperature,maxTemperature,uncertainity,city,country,callback);
	}
}

