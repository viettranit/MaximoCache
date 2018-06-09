package cust.app.location;

import java.rmi.RemoteException;
import java.util.HashSet;

import psdi.mbo.MaximoCache;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.server.MXServer;
import psdi.util.MXException;

public class LocationCache implements MaximoCache {
	public static final String CACHE_NAME = "LOCATION_CACHE";
	private HashSet<String> locationHashSet = new HashSet<String>();
	
	public String getName() {
		return CACHE_NAME;
	}

	public void init() throws MXException {
		loadData();
	}

	public void reload() throws MXException {
		loadData();
	}

	public void reload(String arg0) throws MXException {
		loadData();
	}
	
	public boolean isLocationExist(String locationDesc) {
		return locationHashSet.contains(locationDesc);
	}
	
	private void loadData() {
		try {
			MboSetRemote locationSet = MXServer.getMXServer().getMboSet("LOCATIONS", MXServer.getMXServer().getSystemUserInfo());
			locationSet.setWhere("status = 'OPERATING'");
			locationSet.reset();
			
			for (MboRemote loc = locationSet.moveFirst(); loc != null; loc = locationSet.moveNext()) {
				locationHashSet.add(loc.getString("DESCRIPTION"));
			}
		} catch (MXException mxe) {
			mxe.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
