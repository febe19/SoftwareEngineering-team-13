package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;

public interface Filter {
	public void ascendingOrder();
	public void descendingOrder();
	public void sortByCounty(ArrayList<String> countries);
	public void sortByYear(int year);
	public void sortByYear(int start,int end);
	public void sortByUncertainity(double lowerBound, double upperBound);
	public void sortByCity(ArrayList<String> cities);
	public void sortByLatitude(String latitude);
	public void sortByLongitude(String longitude);
	public void sortByAverageTemperature(double lowerBound,double upperBound);
}
