package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;



public class TableView extends Composite {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	
	// Create the MapViewmainPanel
	public TableView() {

		initContent();
		initWidget(mainPanel);

	}
	//Fill the Main Panel with Stuff
	private void initContent() {
		// SHows COntent in MainPanel
		Label l = new Label("Here we will put the Table");
		mainPanel.addNorth(l,20);
	}

}