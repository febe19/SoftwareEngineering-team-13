package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

//extends TabLayoutPanel
public class WebApp extends DockLayoutPanel implements EntryPoint {
	
	private MapView mapView = new MapView();
	private TableView tableView = new TableView();
	private SelectionPanel selectionPanel = new SelectionPanel();
	private DataSet dataSet;
	
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
		
		//Add tabPanle to north and selectionpanel to south
		addNorth(menu,35);
		addSouth(selectionPanel, 15);
	}
	
	
	@Override
	public void onModuleLoad() {
		WebApp webApp = new WebApp();
		RootLayoutPanel.get().add(webApp);
		
		dataSet = new DataSet();
		dataSet.getData();
		
	}

}
