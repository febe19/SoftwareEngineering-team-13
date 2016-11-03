package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class SelectionPanel extends Composite {

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private HorizontalPanel criteriaPanel = new HorizontalPanel();
	private VerticalPanel countryPanel = new VerticalPanel();
	private VerticalPanel cityPanel = new VerticalPanel();

	private Label country = new Label("Country");
	private TextBox countryIN = new TextBox();
	private Label city = new Label("City");
	private TextBox cityIN = new TextBox();

	public SelectionPanel() {
		initWidget(mainPanel);
		initContent();
	}

	private void initContent() {
		// Shows Content in MainPanel
		Label l = new Label("Select the data you want to see:");
		fillHorizontalPanel();
		mainPanel.addNorth(l, 2);
		mainPanel.addSouth(criteriaPanel, 8);
	}

	private void fillHorizontalPanel() {
		
		country.addStyleName("gwt-FilterLabel");
		city.addStyleName("gwt-FilterLabel");
		
		countryIN.addStyleName("gwt-FilterTextInput");
		cityIN.addStyleName("gwt-FilterTextInput");
		
		countryPanel.add(country);
		countryPanel.add(countryIN);
		criteriaPanel.add(countryPanel);
		cityPanel.add(city);
		cityPanel.add(cityIN);
		criteriaPanel.add(cityPanel);
		
	}

}
