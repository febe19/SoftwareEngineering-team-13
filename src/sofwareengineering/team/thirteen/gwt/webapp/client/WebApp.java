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
		addNorth(menu,35);
		addSouth(selectionPanel, 15);
		selectionPanel.getYearSlider().addSlideStopHandler(new SlideStopHandler<Double>(){
			public void onSlideStop(SlideStopEvent<Double> event){
				mapView.setCurrentYear(Integer.parseInt(String.valueOf(selectionPanel.getYearSlider().getValue())));
				tableView.setCurrentYear(Integer.parseInt(String.valueOf(selectionPanel.getYearSlider().getValue())));
				mapView.fetchData();
				tableView.fetchData();
			}
		});
		selectionPanel.getTempSlider().addSlideStopHandler(new SlideStopHandler<Range>(){

			@Override
			public void onSlideStop(SlideStopEvent<Range> event) {
				mapView.setMaxTemperature(selectionPanel.getTempSlider().getValue().getMaxValue());
				mapView.setMinTemperature(selectionPanel.getTempSlider().getValue().getMinValue());
				mapView.fetchData();
			}
			
		});
		
		selectionPanel.getUncertainitySlider().addSlideStopHandler(new SlideStopHandler<Double>(){

			@Override
			public void onSlideStop(SlideStopEvent<Double> event) {
				mapView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
				tableView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
				mapView.fetchData();
				
			}
			
		});
		selectionPanel.getCheckBox().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					mapView.setUncertainity(selectionPanel.getUncertainitySlider().getValue());
					mapView.fetchData();
				} else {
					mapView.setUncertainity(5);
					mapView.fetchData();
				}
				//mainPanel.clear();
				// initContent();
				// Checkbox not working yet
			}

		});
		
		selectionPanel.getCityIN().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					mapView.setCity("\""+selectionPanel.getCityIN().getValue().toString()+"\"");
					mapView.fetchData();
				}
			}
		});
		
		selectionPanel.getCountryIN().addKeyDownHandler(new KeyDownHandler(){

			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					mapView.setCountry("\""+selectionPanel.getCountryIN().getValue().toString()+"\"");
					mapView.fetchData();
				}
			}
			
		});
		selectionPanel.getResetButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				mapView.setCurrentYear(2013);
				mapView.setMinTemperature(-100);
				mapView.setMaxTemperature(100);
				mapView.setUncertainity(5);
				mapView.setCity("city");
				mapView.setCountry("country");
				mapView.fetchData();
			}
		});
	}
	
	
	@Override
	public void onModuleLoad() {
		WebApp webApp = new WebApp();
		RootLayoutPanel.get().add(webApp);
	}

}
