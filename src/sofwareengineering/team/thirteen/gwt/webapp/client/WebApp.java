package sofwareengineering.team.thirteen.gwt.webapp.client;

import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;

//extends TabLayoutPanel
public class WebApp extends DockLayoutPanel implements EntryPoint {
	
	private MapView mapView = new MapView();
	private TableView tableView = new TableView();
	private SelectionPanel selectionPanel = new SelectionPanel();
	
	public WebApp(){
		//Create DockLayoutPanel -- first Panel inserted to Root Panel
		super(Style.Unit.EM);
		
		//Create TabLayoutPanel and add two tabs
		TabLayoutPanel menu = new TabLayoutPanel(5, Style.Unit.EM);
		
		menu.add(mapView, "Worldmap");
		menu.add(tableView, "Data Table");
		menu.selectTab(0);
		menu.setAnimationDuration(400);
		
		selectionPanel.setStyleName("gwt-SelectionPanel");
		mapView.setStyleName("gwt-TabPanel");
		
		//Add tabPanle to north and selection panel to south
		addNorth(menu,40);
		addSouth(selectionPanel, 15);
		
		//Get the value from the slider when it stops moving
		selectionPanel.getYearSlider().addSlideStopHandler(new SlideStopHandler<Double>(){
			public void onSlideStop(SlideStopEvent<Double> event){
				//Set the year field for mapView and tableView
				mapView.setCurrentYear(Integer.parseInt(String.valueOf(selectionPanel.getYearSlider().getValue())));
				tableView.setCurrentYear(Integer.parseInt(String.valueOf(selectionPanel.getYearSlider().getValue())));
				//Call the "load" function for both mapView and TableView with the new values
				mapView.fetchData();
				tableView.fetchData();
			}
		});
		
		//Get the value from the temperature slider
		selectionPanel.getTempSlider().addSlideStopHandler(new SlideStopHandler<Range>(){
			@Override
			public void onSlideStop(SlideStopEvent<Range> event) {
				//Set the Min and Max temperatures for the mapView
				mapView.setMaxTemperature(selectionPanel.getTempSlider().getValue().getMaxValue());
				mapView.setMinTemperature(selectionPanel.getTempSlider().getValue().getMinValue()); 
				//TODO Set the Min and Max temperature for the TableView
				//Upload the data with the new values
				mapView.fetchData();
				tableView.fetchData();
			}
			
		});
		
		//Get the value from the Uncertainty Slider
		//TODO CHECK THE WHOLE PROJECT AND CHANGE UNCERTAINITY TO UNCERTAINTY (without "I")
		selectionPanel.getUncertainitySlider().addSlideStopHandler(new SlideStopHandler<Double>(){
			@Override
			public void onSlideStop(SlideStopEvent<Double> event) {
				//Set the uncertainty for mapView and table
				mapView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
				tableView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
				//Upload the data with the new values
				mapView.fetchData();
				//TODO add uncertainty to getTableData() method and query and...HAVE FUN!
				tableView.fetchData();
				
			}
			
		});
		
		//Check the Uncertainty's checkbox when is ticked
		selectionPanel.getCheckBox().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//get the current state (checked or unchecked)
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					//Set the uncertainty level to the value on the slider
					mapView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
					//Update 
					mapView.fetchData();
					tableView.fetchData();
				} else {
					//When the checkbox is not ticked, all the temperature have to be displayed
					//therefore we set the uncertainty level to 5 or a value bigger than every 
					//possible uncertainty
					mapView.setUncertainity(0);
					mapView.fetchData();
					tableView.fetchData();
				}
			}

		});
		//Country Box
		selectionPanel.getCityIN().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				//Call this method only when enter is pressed
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					//If it is empty, show all the city
					if(selectionPanel.getCityIN().getValue()==""){
						mapView.setCity("city");
						tableView.fetchData();
					}else{
					//Otherwise get the selected City
					//IMPORTANT: add " at the beginning and at the end of the string for the MySQL query
					mapView.setCity("\""+selectionPanel.getCityIN().getValue().toString()+"\"");
					}
					mapView.fetchData();
					tableView.fetchData();
				}
			}
		});
		
		selectionPanel.getCountryIN().addKeyDownHandler(new KeyDownHandler(){

			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				//Call this method only when enter is pressed
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					//If it is empty, show all the city
					if(selectionPanel.getCountryIN().getValue()==""){
						mapView.setCountry("country");
					}else{
					//Otherwise get the selected City
					//IMPORTANT: add " at the beginning and at the end of the string for the MySQL query
					mapView.setCountry("\""+selectionPanel.getCountryIN().getValue().toString()+"\"");
					}
					mapView.fetchData();
					tableView.fetchData();
				}
			}
			
		});
		
		//Reset Button
		selectionPanel.getResetButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				//Reset all the values to default and reload mapView and tableView
				mapView.setCurrentYear(2013);
				mapView.setMinTemperature(-100);
				mapView.setMaxTemperature(100);
				mapView.setUncertainity(5);
				mapView.setCity("city");
				mapView.setCountry("country");
				mapView.fetchData();
				tableView.fetchData();
			}
		});
	}
	
	
	@Override
	public void onModuleLoad() {
		WebApp webApp = new WebApp();
		RootLayoutPanel.get().add(webApp);
	}

}
