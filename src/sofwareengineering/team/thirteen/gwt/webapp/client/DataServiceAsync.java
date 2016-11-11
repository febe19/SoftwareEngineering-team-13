package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public interface DataServiceAsync {
	void getData(AsyncCallback<ArrayList<DataPoint>> callback);
	void getMapData(int year, AsyncCallback<ArrayList<DataPoint>> callback);
}
