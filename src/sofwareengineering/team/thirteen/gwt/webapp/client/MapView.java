package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;
import com.google.gwt.dom.client.Style;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class MapView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;

	// default attributes that will be changed with the Sliders/Boxes using
	// getters and setters
	private int currentYear = 2013;

	private double maxTemperature = 40;
	private double minTemperature = -30;
	private double uncertainity = 15;

	// all the cities and countries will be shown
	private String city = "city";
	private String country = "country";
	private VerticalPanel infoPanel = new VerticalPanel();
	private Label year = new Label("Year:");
	private Label currentYearLabel = new Label("" + currentYear);
	private Label temperature = new Label("Temperature range in °C:");
	private Label currentTempLabel = new Label("" + getMinTemperature());
	private Label uncertaintyTitle = new Label("Uncertainty in °C:");
	private Label currentUncertaintyLabel = new Label("" + getUncertainity());

	// Create the MapView
	public MapView() {
		initWidget(mainPanel);
		initContent();
	}

	private void fillInfoPanel() {
		currentYearLabel.addStyleName("gwt-DinamicInfoYearLabel");
		currentTempLabel.addStyleName("gwt-DinamicInfoLabel");
		currentUncertaintyLabel.addStyleName("gwt-DinamicInfoLabel");
		year.addStyleName("gwt-fixInfoLabel");
		temperature.addStyleName("gwt-fixInfoLabel");
		uncertaintyTitle.addStyleName("gwt-fixInfoLabel");

		currentYearLabel.setText("" + currentYear);
		currentTempLabel.setText(getMinTemperature() + " <> " + getMaxTemperature());
		currentUncertaintyLabel.setText("" + getUncertainity());

		infoPanel.add(year);
		infoPanel.add(currentYearLabel);
		infoPanel.add(temperature);
		infoPanel.add(currentTempLabel);
		infoPanel.add(uncertaintyTitle);
		infoPanel.add(currentUncertaintyLabel);
	}
	private void initContent() {
		fillInfoPanel();
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				mainPanel.addWest(geoChart, 1100);
				mainPanel.addEast(infoPanel, 300);
				fetchData();
			}
		});
	}

	// Prepare the data which is shown
	// The data will come in form of an ArrayList and can be accessed with
	// getData().get().getRegion()
	private void draw() {
		if (getData() != null) {

			DataTable dataTable = DataTable.create();
			dataTable.addColumn(ColumnType.STRING, "City");
			dataTable.addColumn(ColumnType.NUMBER, "Temperature");

			dataTable.addRows(getData().size());
			for (int i = 0; i < getData().size(); i++) {

				dataTable.setValue(i, 0, getData().get(i).getRegion());
				dataTable.setValue(i, 1,
						getData().get(i).getAverageTemperature());

			}
			// yearLabel.setText("" + currentYear);
			fillInfoPanel();
			currentYearLabel.setText("" + currentYear);
			currentTempLabel.setText(getMinTemperature() + " <> " + getMaxTemperature());
			currentUncertaintyLabel.setText("" + getUncertainity());
			geoChart.draw(dataTable, getGeoChartOptions());
		}
	}

	// Set Style Option for Geochart
	private GeoChartOptions getGeoChartOptions() {
		GeoChartOptions options = GeoChartOptions.create();
		options.setDisplayMode(DisplayMode.MARKERS);
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setMaxValue(40);
		geoChartColorAxis.setMinValue(-30);
		geoChartColorAxis.setColors("FFFFFF", "AAAAFF", "5555FF", "0000FF",
				"0080FF", "00FFFF", "00FF80", "00FF00", "FFFF00", "FFD700",
				"FF8000", "FF0000", "D02A2A", "A65454", "000000");
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("#cecece");
		return options;

	}

	// Method which asks for data from the DataServiceImpl class and then start
	// the drawing of the map

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
		getDataService().getMapData(currentYear, minTemperature, maxTemperature,
				uncertainity, city, country, callback);
	}

	@Override
	public void fetchCountryList() {
		// TODO Auto-generated method stub

	}

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
	public void setCurrentYear(int year) {
		currentYear = year;
	}
	public int getCurrentYear() {
		return currentYear;
	}

}
