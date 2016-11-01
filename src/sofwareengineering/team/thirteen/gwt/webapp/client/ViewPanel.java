package sofwareengineering.team.thirteen.gwt.webapp.client;


import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;


//Special for the Panel with the Filter Criteria
public abstract class ViewPanel extends Composite {
	private DockLayoutPanel mapAndTablePanel = new DockLayoutPanel(Style.Unit.EM);
	private SelectionPanel selectionPanel = new SelectionPanel();
	
	public void createPanel(){
		initWidget(mapAndTablePanel);
		initSelectionPanel();
		
	}
	
	public DockLayoutPanel getMapAndTablePanel() {
        return mapAndTablePanel;

    }

	private void initSelectionPanel() {
		
		
	}
}
