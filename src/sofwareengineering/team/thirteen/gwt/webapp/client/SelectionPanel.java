package sofwareengineering.team.thirteen.gwt.webapp.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.SliderBase;
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
	private TextBox countryIN = new TextBox(); // TODO check for suggestion
	public TextBox getCountryIN() {
		return countryIN;
	}

	// panel
	private Label city = new Label("City");
	private TextBox cityIN = new TextBox();

	public TextBox getCityIN() {
		return cityIN;
	}

	private Label year = new Label("Year");
	private Label temperature = new Label("Temperature");
	private Label uncertainity = new Label("Uncertainity");
	private Slider yearSlider = new Slider();
	private RangeSlider tempSlider = new RangeSlider();
	public RangeSlider getTempSlider() {
		return tempSlider;
	}

	private Slider uncertainitySlider = new Slider();
	
	public Slider getUncertainitySlider() {
		return uncertainitySlider;
	}

	private CheckBox checkBox = new CheckBox();
	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	private Label labelForCheckBox = new Label("Show uncertain Data");

	private Button exportButton = new Button("Export");
	private Button resetButton = new Button("Reset Selection");
	public Button getResetButton() {
		return resetButton;
	}

	private Anchor source = new Anchor("Raw data is from Berkeley Earth.",
			false, "http://www.berkeleyearth.org");

	public SelectionPanel() {
		initWidget(mainPanel);
		initContent();
	}

	public Slider getYearSlider() {
		return yearSlider;
	}

	private void initContent() {
		// Shows Content in MainPanel
		Label infoLabel = new Label("Select the data you want to see:");
		infoLabel.setStyleName("gwt-FilterLabelInfo");
		fillHorizontalPanel();
		mainPanel.addNorth(infoLabel, 2);
		mainPanel.addWest(criteriaPanel, 25);
		mainPanel.addWest(sliderPanel, 100);
		mainPanel.addEast(buttonPanel, 20);
		// Action when slider is moved

		// Action When reset Button is clicked
		resetButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				// TODO ResetFilter
			}
		});

		// Action when exportButton is clicked
		exportButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				// TODO open Export Function
			}
		});

		// Action when Enter is pressed after text input
		countryIN.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					// TODO reload data with filter criteria included
				}
			}
		});
		// Action when Enter is pressed after text input
		cityIN.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent keyDownEvent) {
				if (keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					// TODO reload data with filter criteria included
				}
			}
		});

		// TODO slider Input

		// Checks the checkBox and enable/disable the UncertainitySlider
		checkBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				if (checked) {
					uncertainitySlider.setEnabled(true);
				} else {
					uncertainitySlider.setEnabled(false);
				}
				//mainPanel.clear();
				// initContent();
				// Checkbox not working yet
			}

		});

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
		labelForCheckBox.setStyleName("gwt-CheckBoxLabel");
		
		yearPanel.add(year);
		yearPanel.add(yearSlider);
		tempPanel.add(temperature);
		tempPanel.add(tempSlider);
		uncertainityPanel.add(uncertainity);
		uncertainitySliderAndToggle.add(uncertainitySlider);
		uncertainitySliderAndToggle.add(labelForCheckBox);
		uncertainitySliderAndToggle.add(checkBox);
		checkBox.setValue(true);
		uncertainityPanel.add(uncertainitySliderAndToggle);
		sliderPanel.add(yearPanel);
		sliderPanel.add(tempPanel);
		sliderPanel.add(uncertainityPanel);

		initButtonStile();
		buttonPanel.add(exportButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(source);

	}

	// Reset And Export Button Style
	private void initButtonStile() {
		exportButton.setStyleName("gwt-SelectionButton");
		resetButton.setStyleName("gwt-SelectionButton");
		exportButton.setWidth("200px");
		resetButton.setWidth("200px");
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

		yearSlider.setMin(1849);
		yearSlider.setMax(2013);
		yearSlider.setWidth("700px");

		tempSlider.setMin(-30);
		tempSlider.setMax(40);
		tempSlider.setStep(0.5);
		tempSlider.setWidth("700px");

		uncertainitySlider.setMin(0);
		uncertainitySlider.setMax(15);
		uncertainitySlider.setStep(0.05);
		uncertainitySlider.setWidth("500px");
	}
	

}
