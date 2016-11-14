package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopHandler;

public class SelectionPanel extends Composite {

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
	private TextBox countryIN = new TextBox();		//TODO check for suggestion panel 
	private Label city = new Label("City");
	private TextBox cityIN = new TextBox();

	private Label year = new Label("Year");
	private Label temperature = new Label("Temperature");
	private Label uncertainity = new Label("Uncertainity");
	private Slider yearSlider = new Slider();
	private RangeSlider tempSlider = new RangeSlider();
	private Slider uncertainitySlider = new Slider();
//	private ToggleButton showUncertainity = new ToggleButton("Show uncertain Data -- ToggleButton");
	

	//private Button exportButton = new Button("Export");
	//private Button resetButton = new Button("Reset Selection");
	private Anchor source = new Anchor("Raw data is from Berkeley Earth.", false, "http://www.berkeleyearth.org");

	public SelectionPanel() {
		initWidget(mainPanel);
		initContent();
	}

	private void initContent() {
		// Shows Content in MainPanel
//		Label infoLabel = new Label("Select the data you want to see:");
//			infoLabel.setStyleName("gwt-FilterLabelInfo");
		fillHorizontalPanel();
//		mainPanel.addNorth(infoLabel, 2);
		mainPanel.addWest(criteriaPanel, 25);
		mainPanel.addWest(sliderPanel, 100);
		mainPanel.addEast(buttonPanel, 20);

		// Action When reset Button is clicked
//		resetButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent clickEvent) {
//				// TODO ResetFilter
//			}
//		});

		// Action when exportButton is clicked
//		exportButton.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent clickEvent) {
//				// TODO open Export Function
//			}
//		});
		
		// Action when Enter is pressed after text input
//		countryIN.addKeyDownHandler(new KeyDownHandler() {
//            @Override
//            public void onKeyDown(KeyDownEvent keyDownEvent) {
//                if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//                   // TODO reload data with filter criteria included
//                }
//            }
//        });
//		// Action when Enter is pressed after text input
//		cityIN.addKeyDownHandler(new KeyDownHandler() {
//            @Override
//            public void onKeyDown(KeyDownEvent keyDownEvent) {
//                if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//                   // TODO reload data with filter criteria included
//                }
//            }
//        });
		
		
	//TODO slider Input 
		
	}

	private void fillHorizontalPanel() {
//		// Add country & City Label and textImput to selectionPanel - left side.
//		country.addStyleName("gwt-FilterLabel");
//		city.addStyleName("gwt-FilterLabel");
//		countryIN.addStyleName("gwt-FilterTextInput");
//		cityIN.addStyleName("gwt-FilterTextInput");
//		countryPanel.add(country);
//		countryPanel.add(countryIN);
//		criteriaPanel.add(countryPanel);
//		cityPanel.add(city);
//		cityPanel.add(cityIN);
//		criteriaPanel.add(cityPanel);

		// Add Slider Panel to selectionPanel - middle.
		initStyleForSlider();
		yearPanel.add(year);
		yearPanel.add(yearSlider);
		tempPanel.add(temperature);
		tempPanel.add(tempSlider);
		uncertainityPanel.add(uncertainity);
		uncertainitySliderAndToggle.add(uncertainitySlider);
//		uncertainitySliderAndToggle.add(showUncertainity);
		uncertainityPanel.add(uncertainitySliderAndToggle);
		sliderPanel.add(yearPanel);
		sliderPanel.add(tempPanel);
		sliderPanel.add(uncertainityPanel);

//		initButtonStile();
//		buttonPanel.add(exportButton);
//		buttonPanel.add(resetButton);
		buttonPanel.add(source);

	}

	// Reset And Export Button Style
//	private void initButtonStile() {
//		exportButton.setStyleName("gwt-SelectionButton");
//		resetButton.setStyleName("gwt-SelectionButton");
//		exportButton.setWidth("200px");
//		resetButton.setWidth("200px");
//	}

	// Slider Style
	private void initStyleForSlider() {
		// TODO anpassung der Mindest- und Maximaltemperatur.

		year.setStyleName("gwt-SliderLabel");
		temperature.setStyleName("gwt-SliderLabel");
		uncertainity.setStyleName("gwt-SliderLabel");

		yearSlider.setStyleName("gwt-Slider");
		tempSlider.setStyleName("gwt-Slider");
		uncertainitySlider.setStyleName("gwt-Slider");

		yearSlider.setMin(1849);
		yearSlider.setMax(2013);
		yearSlider.setWidth("700px");

		tempSlider.setMin(-10);
		tempSlider.setMax(60);
		tempSlider.setStep(0.5);
		tempSlider.setWidth("700px");

		uncertainitySlider.setMin(0);
		uncertainitySlider.setMax(3);
		uncertainitySlider.setStep(0.05);
		uncertainitySlider.setWidth("200px");
	}

}
