package sofwareengineering.team.thirteen.gwt.webapp.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import sofwareengineering.team.thirteen.gwt.webapp.shared.DataPoint;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService{
	List<DataPoint> getData();

}
