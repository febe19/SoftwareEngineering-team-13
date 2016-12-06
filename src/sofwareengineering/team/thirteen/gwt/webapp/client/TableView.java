package sofwareengineering.team.thirteen.gwt.webapp.client;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.ListDataProvider;
import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;


public class TableView extends DataView {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private CellTable<DataPoint> dataTable = new CellTable<DataPoint>();
	private int currentYear=2013;
	private double uncertainity=15;
	private double minTemperature = -30;
	private double maxTemperature = 40;
	private String country = "country";
	private String city ="city";
	//important for updating the table
	private boolean firstTime=true;
	ScrollPanel scrollPanel = new ScrollPanel(dataTable);
	private List<DataPoint> Countries = new ArrayList<>();
	private List<DataPoint> Cities = new ArrayList<>();
	
	// Create the MapViewMainPanel
	public TableView() {
		fetchCountryList();
		fetchCityList();
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
		if(getTableData()!=null){
			

			for (DataPoint p : getTableData()) {
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
	        
	        //Latitude Sort
	        columnSortHandler.setComparator(dataTable.getColumn(5),
	        		new Comparator<DataPoint>() {
	        	public int compare(DataPoint o1, DataPoint o2) {
	        		if (o1 == o2) {
	        			return 0;
	        		}

	        		// Compare the name columns.
	        		if (o1 != null) {
	        			return (o2 != null) ? o1.getLatitude().compareTo(o2.getLatitude()) : 1;
	        		}
	        		return -1;
	        	}
	        });
	        dataTable.addColumnSortHandler(columnSortHandler);
	        //Longitude Sort
	        columnSortHandler.setComparator(dataTable.getColumn(6),
	        		new Comparator<DataPoint>() {
	        	public int compare(DataPoint o1, DataPoint o2) {
	        		if (o1 == o2) {
	        			return 0;
	        		}

	        		// Compare the name columns.
	        		if (o1 != null) {
	        			return (o2 != null) ? o1.getLongitude().compareTo(o2.getLongitude()) : 1;
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
				return object.getRegion();
			}
        };
        TextColumn<DataPoint> temperatureColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				return String.valueOf(object.getAverageTemperature());
			}
        };
        TextColumn<DataPoint> uncertainityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				return String.valueOf(object.getUncertainity());
			}
        };
        TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				return object.getDate().toString();
			}
        };
        TextColumn<DataPoint> latitudeColumn = new TextColumn<DataPoint>(){
        	@Override
        	public String getValue(DataPoint object) {
        		return object.getLatitude();
        	}
        };
        TextColumn<DataPoint> longitudeColumn = new TextColumn<DataPoint>(){
        	@Override
        	public String getValue(DataPoint object) {
        		return object.getLongitude();
        	}
        };

        //Set columns as sortable
        cityColumn.setSortable(true);
        countryColumn.setSortable(true);
        temperatureColumn.setSortable(true);
        uncertainityColumn.setSortable(true);
        dateColumn.setSortable(true);
        latitudeColumn.setSortable(true);
        longitudeColumn.setSortable(true);
        
        //Column name in database
        countryColumn.setDataStoreName("Country");
        cityColumn.setDataStoreName("City");
        temperatureColumn.setDataStoreName("Temperature");
        uncertainityColumn.setDataStoreName("Uncertainty");
        dateColumn.setDataStoreName("Date");
        latitudeColumn.setDataStoreName("Lat");
        latitudeColumn.setDataStoreName("Lon");
        
        //column header is "Name"
        //add columns to the table
        dataTable.addColumn(countryColumn, "Country");
        dataTable.addColumn(cityColumn, "City");
        dataTable.addColumn(temperatureColumn, "Temperature");
        dataTable.addColumn(uncertainityColumn, "Uncertainity");
        dataTable.addColumn(dateColumn, "Date");
        dataTable.addColumn(latitudeColumn,"Latitude");
        dataTable.addColumn(longitudeColumn,"Longitude");


	}

	
	
	@Override
	public void fetchData() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setTableData(result);
				//initialize the Table once the data from the database are ready to avoid errors
				initContent();		
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				GWT.log("FetchData Failed");
			}
		};
		// call to server
		getDataService().getTableData(currentYear,minTemperature, maxTemperature, uncertainity,city, country, callback);
	}
	
	public void fetchCountryList() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				setCountryList(result);
				setCountriesList();
				//initialize the Table once the data from the database are ready to avoid errors
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Do something
				GWT.log("fetchCountryList failed");
			}
		};
		// call to server
		getDataService().getCountryList(callback);
	}
	
	public void fetchCityList() {
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				
				setCityList(result);
				setCitiesList();
				//initialize the Table once the data from the database are ready to avoid errors
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("fetchCityList failed");
			}
		};
		// call to server
		getDataService().getCityList(country,callback);
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
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	private void setCountriesList(){
		Countries = getCountryList2();
	}
	public List<DataPoint> getCountries() {
		return Countries;
	}
	public void setCountries(List<DataPoint> countries) {
		Countries = countries;
	}
	private void setCitiesList(){
		Cities = getCityList2();
	}
	public List<DataPoint> getCities() {
		return Cities;
	}
	public void setCities(List<DataPoint> cities) {
		Cities = cities;
	}
}