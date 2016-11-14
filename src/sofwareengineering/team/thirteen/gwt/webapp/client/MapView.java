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

public class MapView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;
	// private Logger l = new Logger("MapViewLog");

	// Create the MapView
	public MapView() {
		initWidget(mainPanel);
		initContent();
	}

	private void initContent() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				mainPanel.add(geoChart);
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

//				GWT.log(getData().get(i).getRegion());
			}
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
		options.setDatalessRegionColor("#373737");
		return options;

	}

	// Method which asks for data from the DataServiceImpl class and then start
	// the drawing of the map
	@Override
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

		// call to server
		getDataService().getMapData(2011,callback);
	}
}
