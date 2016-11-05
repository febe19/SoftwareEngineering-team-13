package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class MapView extends Composite {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;

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
				draw();
			}
		});
	}

	private void draw() {
		// Prepare the data which is shown

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "City");
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addRows(12);
		dataTable.setValue(0, 0, "Rome");
		dataTable.setValue(0, 1, 25);
		dataTable.setValue(1, 0, "Paris");
		dataTable.setValue(1, 1, 15);
		dataTable.setValue(2, 0, "New York");
		dataTable.setValue(2, 1, 10);
		dataTable.setValue(3, 0, "Hong Kong");
		dataTable.setValue(3, 1, 28);
		dataTable.setValue(4, 0, "Sydney");
		dataTable.setValue(4, 1, 32);
		dataTable.setValue(5, 0, "Miami");
		dataTable.setValue(5, 1, 27);
		dataTable.setValue(6, 0, "Istanbul");
		dataTable.setValue(6, 1, 27);
		dataTable.setValue(7, 0, "Oslo");
		dataTable.setValue(7, 1, -5);
		dataTable.setValue(8, 0, "Vancouver");
		dataTable.setValue(8, 1, -2);
		dataTable.setValue(9, 0, "Buenos Aires");
		dataTable.setValue(9, 1, 12);
		dataTable.setValue(10, 0, "Kapstadt");
		dataTable.setValue(10, 1, 0);
		dataTable.setValue(11, 0, "Tokyo");
		dataTable.setValue(11, 1, 3);

		geoChart.draw(dataTable, getGeoChartOptions());
	}

	// Set Style Option for Geocart 											TODO: Size of the markers
	private GeoChartOptions getGeoChartOptions() {
		GeoChartOptions options = GeoChartOptions.create();
		options.setDisplayMode(DisplayMode.MARKERS);
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors(getNativeArray());
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("#373737");
		return options;

	}
	// Set Color for Markers
	private native JsArrayString getNativeArray() /*-{
		return [ "0000FF", "5858FA", "A9A9F5", "F7819F", "FE2E64", "FF0040" ];
	}-*/;

}
