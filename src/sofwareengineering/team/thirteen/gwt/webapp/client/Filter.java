package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;

public interface Filter {
	public void ascendingOrder();
	public void descendingOrder();
	public void sortByCounty(ArrayList<String> countries);
	public void sortByYear(int year);
	
	
	
}
