package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

//extends TabLayoutPanel
public class WebApp extends DockLayoutPanel implements EntryPoint {
	
	private MapView mapView = new MapView();
	private TableView tableView = new TableView();
	private SelectionPanel selectionPanel = new SelectionPanel();
	private Button B = new Button("JUMPer");
	
	public WebApp(){
		
		super(Style.Unit.EM);
		
		TabLayoutPanel menu = new TabLayoutPanel(5, Style.Unit.EM);
		
		menu.add(mapView, "Worldmap");
		menu.add(tableView, "Data Table");
		menu.selectTab(0);
		menu.setAnimationDuration(400);
		
		
		addNorth(menu,70);
		//addSouth(B,10);
		addSouth(selectionPanel, 10);
	}
	
	
	@Override
	public void onModuleLoad() {
		WebApp webApp = new WebApp();
				
		RootLayoutPanel.get().add(webApp);
		
	}

}
