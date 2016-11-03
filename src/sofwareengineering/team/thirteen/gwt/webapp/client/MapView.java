package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;


public class MapView extends Composite {
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	
	//Create the MapView
	public MapView(){
		initWidget(mainPanel);
		initContent();
	}	
	
	private void initContent() {
		// Shows COntent in MainPanel
		Label l = new Label("Here we will put the Geo Chart of the World map");
		mainPanel.addNorth(l,20);
	}
	
}

