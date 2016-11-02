package sofwareengineering.team.thirteen.gwt.webapp.client;


import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;

public class SelectionPanel extends Composite {
	
	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);

	public SelectionPanel(){
		initWidget(mainPanel);
		initContent();	
	}
	
	
	private void initContent() {
		// SHows COntent in MainPanel
		Label l = new Label("Here you can choose betwen filters and stuff like specific countries");
		mainPanel.addNorth(l,20);
	}

}

