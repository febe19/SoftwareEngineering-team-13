package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;


public class MapView extends Composite {
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.PX);
	private GeoChart geoChart;
	
	//Create the MapView
	public MapView(){
		initWidget(mainPanel);
		initContent();
	}	
	
	private void initContent() {
		// Shows COntent in MainPanel
		/*Label l = new Label("Here we will put the Geo Chart of the World map");
		mainPanel.addNorth(l,20);*/
		
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
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.NUMBER, "Popularity");
		dataTable.addRows(6);
		dataTable.setValue(0, 0, "Germany");
		dataTable.setValue(0, 1, 200);
		dataTable.setValue(1, 0, "United States");
		dataTable.setValue(1, 1, 300);
		dataTable.setValue(2, 0, "Brazil");
		dataTable.setValue(2, 1, 400);
		dataTable.setValue(3, 0, "Canada");
		dataTable.setValue(3, 1, 500);
		dataTable.setValue(4, 0, "France");
		dataTable.setValue(4, 1, 600);
		dataTable.setValue(5, 0, "RU");
		dataTable.setValue(5, 1, 700);

		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		/*geoChartColorAxis.setColors("green", "yellow", "red");
		options.setColorAxis(geoChartColorAxis);*/
		options.setDatalessRegionColor("gray");

		// Draw the chart
		geoChart.draw(dataTable, options);
	}
	
}

