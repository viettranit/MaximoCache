package cust.app.location;

import java.rmi.RemoteException;

import psdi.server.AppService;
import psdi.server.MXServer;

public class LocationCheckService extends AppService implements LocationCheckServiceRemote{

	public static final String SERVICE_NAME = "LOCATION_CHECK_SERVICE";
	
	public LocationCheckService(MXServer ms) throws RemoteException {
		super(ms);
		name = SERVICE_NAME;
	}
	
	public void init() {
		super.init();
		
		LocationCache cache = new LocationCache();
		try {
			cache.init();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		mxServer.addToMaximoCache(cache.getName(), cache);
	}
}
