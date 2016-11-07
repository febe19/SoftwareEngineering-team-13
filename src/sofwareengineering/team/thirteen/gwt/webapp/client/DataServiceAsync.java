package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

public interface DataServiceAsync {
	void getData(AsyncCallback<List<DataPoint>> callback);
}
