package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;


public class WebApp extends TabLayoutPanel implements EntryPoint {
	
	private ViewPanel mapView = new MapView();
	private ViewPanel tableView = new TableView();
	
	
	public WebApp(){
		super(5,Style.Unit.EM);
		setAnimationDuration(300);
		
		add(mapView, "Worldmap");
		add(tableView, "Data Table");
		
		selectTab(0);
		
	}
	
	
	@Override
	public void onModuleLoad() {
		WebApp webApp = new WebApp();
				
		RootLayoutPanel.get().add(webApp);
		
	}

}
