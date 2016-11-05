package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class TableView extends Composite {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private CellTable<DataPoint> dataTable = new CellTable<DataPoint>();

	// Create the MapViewMainPanel
	public TableView() {

		initContent();
		initWidget(mainPanel);

	}

	// Fill the Main Panel with Stuff
	private void initContent() {
		// CHeck if Table is Empty
		dataTable.setEmptyTableWidget(new Label("No Movies Found"));
		
		initTableColumns();
		
		mainPanel.add(dataTable);
	}

	private void initTableColumns() {
        //Movie Name column
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO Auto-generated method stub
				return null;
			}
        };
        
        TextColumn<DataPoint> cityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return null;
			}
        };
        TextColumn<DataPoint> temperatureColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return null;
			}
        };
        TextColumn<DataPoint> uncertainityColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO get the data and return it
				return null;
			}
        };
        TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>(){
  			@Override
			public String getValue(DataPoint object) {
				// TODO Get the data and return it
				return null;
			}
        };
        //column is sortable
        cityColumn.setSortable(true);
        countryColumn.setSortable(true);
        temperatureColumn.setSortable(true);
        uncertainityColumn.setSortable(true);
        dateColumn.setSortable(true);
        //column header is "Name"
        dataTable.addColumn(countryColumn, "Country");
        dataTable.addColumn(cityColumn, "City");
        dataTable.addColumn(temperatureColumn, "Temperature");
        dataTable.addColumn(uncertainityColumn, "Uncertainity");
        dataTable.addColumn(dateColumn, "Date");
        
	}
}