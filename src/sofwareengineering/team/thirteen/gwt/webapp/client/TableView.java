package sofwareengineering.team.thirteen.gwt.webapp.client;


import java.util.ArrayList;
import java.util.Comparator;
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
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public class TableView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private CellTable<DataPoint> dataTable = new CellTable<DataPoint>();
	private int currentYear=2013;
	private double uncertainity=5;
	private double checkboxUncertainity = 5;
	//important for updating the table
	private boolean firstTime=true;
	ScrollPanel scrollPanel = new ScrollPanel(dataTable);


	// Create the MapViewMainPanel
	public TableView() {
		fetchData();
		initWidget(mainPanel);

	}
	// Fill the Main Panel with Stuff
	private void initContent() {
		
		// Check if Table is Empty
		dataTable.setEmptyTableWidget(new Label("No data found"));
		
		//Instantiate the columns only once and not each time
		if(firstTime){
			firstTime=false;
			initTableColumns();
		}
		
		//Add data to the table
		ListDataProvider<DataPoint> dataProvider = new ListDataProvider<DataPoint>();
		dataProvider.addDataDisplay(dataTable);
		List<DataPoint> list = dataProvider.getList();
		if(getData()!=null){
			

			for (DataPoint p : getData()) {
				list.add(p);
			}
		}
		
		//Temperature Sort
		ListHandler<DataPoint> columnSortHandler = new ListHandler<DataPoint>(list);
	    columnSortHandler.setComparator(dataTable.getColumn(2),
	            new Comparator<DataPoint>() {
	              public int compare(DataPoint o1, DataPoint o2) {
	                if (o1 == o2) {
	                  return 0;
	                }

	                // Compare the name columns.
	                if (o1 != null) {
	                	Double obj1 = new Double(o1.getAverageTemperature());
	                    Double obj2 = new Double(o2.getAverageTemperature());
	                  return (o2 != null) ? obj1.compareTo(obj2) : 1;
	                }
	                return -1;
	              }
	            });
	    dataTable.addColumnSortHandler(columnSortHandler);
	    
	    //Uncertainty Sort
	    columnSortHandler.setComparator(dataTable.getColumn(3),
	            new Comparator<DataPoint>() {
	              public int compare(DataPoint o1, DataPoint o2) {
	                if (o1 == o2) {
	                  return 0;
	                }

	                // Compare the name columns.
	                if (o1 != null) {
	                	Double obj1 = new Double(o1.getUncertainity());
	                    Double obj2 = new Double(o2.getUncertainity());
	                    if(o2!=null){
	                    	return obj1.compareTo(obj2);
	                    }else{
	                    	return 1;
	                    }
	                }
	                return -1;
	              }
	            });
	        dataTable.addColumnSortHandler(columnSortHandler);
	        
	      //Country Sort
		    columnSortHandler.setComparator(dataTable.getColumn(0),
		            new Comparator<DataPoint>() {
		              public int compare(DataPoint o1, DataPoint o2) {
		                if (o1 == o2) {
		                  return 0;
		                }

		                // Compare the name columns.
		                if (o1 != null) {
		                  return (o2 != null) ? o1.getCountry().compareTo(o2.getCountry()) : 1;
		                }
		                return -1;
		              }
		            });
		        dataTable.addColumnSortHandler(columnSortHandler);
		        
	        //City Sort
	        columnSortHandler.setComparator(dataTable.getColumn(1),
	        		new Comparator<DataPoint>() {
	        	public int compare(DataPoint o1, DataPoint o2) {
	        		if (o1 == o2) {
	        			return 0;
	        		}

	        		// Compare the name columns.
	        		if (o1 != null) {
	        			return (o2 != null) ? o1.getRegion().compareTo(o2.getRegion()) : 1;
	        		}
	        		return -1;
	        	}
	        });
	        dataTable.addColumnSortHandler(columnSortHandler);
	        
	        //Date Sort
	        columnSortHandler.setComparator(dataTable.getColumn(4),
	        		new Comparator<DataPoint>() {
	        	public int compare(DataPoint o1, DataPoint o2) {
	        		if (o1 == o2) {
	        			return 0;
	        		}

	        		// Compare the name columns.
	        		if (o1 != null) {
	        			return (o2 != null) ? o1.getDate().compareTo(o2.getDate()) : 1;
	        		}
	        		return -1;
	        	}
	        });
	        dataTable.addColumnSortHandler(columnSortHandler);
	        //Show the whole table in the scrollPabel and not only 10 entries (by default)
	        dataTable.setVisibleRange(0,list.size());
	        //Enable the scrolling function
	        scrollPanel.setAlwaysShowScrollBars(true);
	        mainPanel.add(scrollPanel);
		
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
        //Set columns as sortable
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
        //add columns to the table
        dataTable.addColumn(countryColumn, "Country");
        dataTable.addColumn(cityColumn, "City");
        dataTable.addColumn(temperatureColumn, "Temperature");
        dataTable.addColumn(uncertainityColumn, "Uncertainity");
        dataTable.addColumn(dateColumn, "Date");


	}

	public double getUncertainity() {
		return uncertainity;
	}
	public void setUncertainity(double uncertainity) {
		this.uncertainity = uncertainity;
	}
	public void setCurrentYear(int year){
		currentYear=year;
	}
	
	@Override
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				//initialize the Table once the data from the database are ready to avoid errors
				initContent();		
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				GWT.log("Failed");
			}
		};
		// call to server
		getDataService().getTableData(currentYear,callback);
	}
}