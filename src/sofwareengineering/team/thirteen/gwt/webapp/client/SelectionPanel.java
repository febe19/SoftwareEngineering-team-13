package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;

public class SelectionPanel extends Composite{

	private DockLayoutPanel mainPanel = new DockLayoutPanel(Style.Unit.EM);
	private VerticalPanel criteriaPanel = new VerticalPanel();
	private VerticalPanel sliderPanel = new VerticalPanel();
	private VerticalPanel countryPanel = new VerticalPanel();
	private VerticalPanel cityPanel = new VerticalPanel();
	private VerticalPanel yearPanel = new VerticalPanel();
	private VerticalPanel tempPanel = new VerticalPanel();
	private VerticalPanel uncertainityPanel = new VerticalPanel();
	private HorizontalPanel uncertainitySliderAndToggle = new HorizontalPanel();
	private VerticalPanel buttonPanel = new VerticalPanel();
	private Label country = new Label("Country");
	private ListBox countryIN = new ListBox(); 
	private Label city = new Label("City");
	private ListBox cityIN = new ListBox();
	private Label year = new Label("Year");
	private Label temperature = new Label("Temperature in °C");
	private Label uncertainity = new Label("Uncertainty in °C");
	private Slider yearSlider = new Slider();
	private RangeSlider tempSlider = new RangeSlider();
	private Slider uncertainitySlider = new Slider();
	private Button resetButton = new Button("Reset Selection");
	private Anchor source = new Anchor("Raw data is from Berkeley Earth.",
			false, "http://www.berkeleyearth.org");
	Label infoLabel = new Label("Select the data you want to see:");
	
	
	public ListBox getCountryIN() {
		return countryIN;
	}

	public ListBox getCityIN() {
		return cityIN;
	}
	
	public RangeSlider getTempSlider() {
		return tempSlider;
	}
	
	public Slider getUncertainitySlider() {
		return uncertainitySlider;
	}

	
	public Button getResetButton() {
		return resetButton;
	}

	public Slider getYearSlider() {
		return yearSlider;
	}

	// Mapview starts in this method
	public SelectionPanel() {
		initWidget(mainPanel);
		initContent();
	}
	
	private void initContent() {
		// Shows Content in MainPanel
		infoLabel.setStyleName("gwt-FilterLabelInfo");
		fillHorizontalPanel();
		mainPanel.addNorth(infoLabel, 2);
		mainPanel.addWest(criteriaPanel, 25);
		mainPanel.addWest(sliderPanel, 100);
		mainPanel.addEast(buttonPanel, 20);
	}

	private void fillHorizontalPanel() {
		// Add country & City Label and textImput to selectionPanel - left side.
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

		// Add Slider Panel to selectionPanel - middle.
		initStyleForSlider();
		
		yearPanel.add(year);
		yearPanel.add(yearSlider);
		tempPanel.add(temperature);
		tempPanel.add(tempSlider);
		uncertainityPanel.add(uncertainity);
		uncertainitySliderAndToggle.add(uncertainitySlider);
		uncertainityPanel.add(uncertainitySliderAndToggle);
		sliderPanel.add(yearPanel);
		sliderPanel.add(tempPanel);
		sliderPanel.add(uncertainityPanel);

		initButtonStile();
		buttonPanel.add(resetButton);
		buttonPanel.add(source);

	}

	// Reset Button and CityIN / CountryIN Style
	private void initButtonStile() {
		resetButton.setStyleName("gwt-SelectionButton");
		resetButton.setWidth("200px");
		countryIN.setWidth("300px");
		cityIN.setWidth("300px");	
	}

	
	// Slider Style
	private void initStyleForSlider() {
		// TODO anpassung der Mindest- und Maximaltemperatur.

		year.setStyleName("gwt-SliderLabel");
		temperature.setStyleName("gwt-SliderLabel");
		uncertainity.setStyleName("gwt-SliderLabel");

		yearSlider.setStyleName("gwt-Slider");
		tempSlider.setStyleName("gwt-Slider");
		uncertainitySlider.setStyleName("gwt-Slider");

		yearSlider.setMin(1743);
		yearSlider.setMax(2013);
		yearSlider.setWidth("700px");

		tempSlider.setMin(-30);
		tempSlider.setMax(40);
		tempSlider.setStep(0.5);
		tempSlider.setWidth("700px");

		uncertainitySlider.setMin(0);
		uncertainitySlider.setMax(15);
		uncertainitySlider.setStep(0.05);
		uncertainitySlider.setWidth("700px");
	}
	

}
