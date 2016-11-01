package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class WebApp implements EntryPoint {
	
	private HorizontalPanel panelForMenuButton = new HorizontalPanel();
	private Button buttonMap = new Button("Worldmap");
	private Button buttonTable = new Button("Table");
	
	private HorizontalPanel worldMapPanel = new HorizontalPanel();
	
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		panelForMenuButton.add(buttonMap);
		panelForMenuButton.add(buttonTable);
		
		final Image worldMap = new Image();
		worldMap.setUrl("http://www.electrogarden.com/music/egn/skins/ProJam_Dark/images/world_map_black.jpg");
		
		worldMapPanel.add(worldMap);
		
		RootPanel.get("App").add(panelForMenuButton);
		RootPanel.get("App").add(worldMapPanel);
		
	} 
	

}



