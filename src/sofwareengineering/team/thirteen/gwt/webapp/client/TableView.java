package sofwareengineering.team.thirteen.gwt.webapp.client;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.user.client.Timer;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class TableView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private CellTable<DataPoint> dataTable = new CellTable<DataPoint>();
	// Create the MapViewMainPanel
	public TableView() {
		fetchData();
		initWidget(mainPanel);

	}
	// Fill the Main Panel with Stuff
	private void initContent() {
		
		// CHeck if Table is Empty
		dataTable.setEmptyTableWidget(new Label("No data found"));
		
		initTableColumns();
		
		ListDataProvider<DataPoint> dataProvider = new ListDataProvider<DataPoint>();
		dataProvider.addDataDisplay(dataTable);
		if(getData()!=null){
			List<DataPoint> list = dataProvider.getList();

			for (DataPoint p : getData()) {
				list.add(p);
			}
		}
//		GWT.log("fuori if");
		/*
		DataPoint p = new DataPoint();
		p.setCountry("asd");
		p.setRegion("gay");
		p.setAverageTemperature(17);
		p.setUncertainity(2);
		Date date = new Date(2013,13,3);
		p.setDate(date);
		List<DataPoint> list = dataProvider.getList();
		list.add(p);
		*/
		mainPanel.add(dataTable);
		
	}

	private void initTableColumns() {
        //Data Name column
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
  				return object.getCountry();
			}
        };
        
        TextColumn<DataPoint> cityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return object.getRegion();
			}
        };
        TextColumn<DataPoint> temperatureColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return String.valueOf(object.getAverageTemperature());
			}
        };
        TextColumn<DataPoint> uncertainityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return String.valueOf(object.getUncertainity());
			}
        };
        TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO Get the data and return it
				return object.getDate().toString();
			}
        };
        //column is sortable
        cityColumn.setSortable(true);
        countryColumn.setSortable(true);
        temperatureColumn.setSortable(true);
        uncertainityColumn.setSortable(true);
        dateColumn.setSortable(true);
        //Column name in database
        countryColumn.setDataStoreName("Country");
        cityColumn.setDataStoreName("City");
        temperatureColumn.setDataStoreName("Temperature");
        uncertainityColumn.setDataStoreName("Uncertainty");
        dateColumn.setDataStoreName("Date");
        //column header is "Name"
        dataTable.addColumn(countryColumn, "Country");
        dataTable.addColumn(cityColumn, "City");
        dataTable.addColumn(temperatureColumn, "Temperature");
        dataTable.addColumn(uncertainityColumn, "Uncertainity");
        dataTable.addColumn(dateColumn, "Date");


	}

	@Override
	public void fetchData() {
		GWT.log("fetchData");
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				GWT.log("Sucess");
				setData(result);
				initContent();
				
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				GWT.log("Failure");
			}
		};
		// call to server
		getDataService().getTableData(callback);
	}
}