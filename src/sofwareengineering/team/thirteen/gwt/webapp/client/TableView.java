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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
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
		
		// Check if Table is Empty
		dataTable.setEmptyTableWidget(new Label("No data found"));
		initTableColumns();
		
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
	                    	//GWT.log(String.valueOf(obj1.compareTo(obj2)));
	                    	return obj1.compareTo(obj2);
	                    }else{
	                    	return 1;
	                    }
	                 // return (o2 != null) ? obj1.compareTo(obj2) : 1;
	                }
	                return -1;
	              }
	            });
	        dataTable.addColumnSortHandler(columnSortHandler);
	        
	      //Country Sort (NOT YET IMPLEMENTED)
		    columnSortHandler.setComparator(dataTable.getColumn(0),
		            new Comparator<DataPoint>() {
		              public int compare(DataPoint o1, DataPoint o2) {
		                if (o1 == o2) {
		                  return 0;
		                }

		                // Compare the name columns.
		                if (o1 != null) {
//		                	Double obj1 = new Double(o1.getCountry());
//		                    Double obj2 = new Double(o2.getUncertainity());
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
//	        dataTable.setPageSize(50);
	        dataTable.setVisibleRange(0,list.size());
	        GWT.log(String.valueOf(dataTable.getVisibleItemCount()));
	        GWT.log(String.valueOf(list.size()));
	        // We know that the data is sorted alphabetically by default.
	        //Not sure what it does or if we really need it
	        //dataTable.getColumnSortList().push(dataTable.getColumn(2));
	        ScrollPanel scrollPanel = new ScrollPanel(dataTable);
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
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setData(result);
				initContent();
				
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				GWT.log("Failed");
			}
		};
		// call to server
		getDataService().getTableData(callback);
	}
}