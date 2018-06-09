package cust.app.item;

import java.rmi.RemoteException;

import psdi.mbo.MboValue;
import psdi.mbo.MboValueAdapter;
import psdi.server.MXServer;
import psdi.util.MXApplicationException;
import psdi.util.MXException;
import cust.app.location.LocationCache;

public class FldDescription extends MboValueAdapter {
	public FldDescription(MboValue mbv) {
		super(mbv);
	}
	
	public void validate() throws MXException {
		try {
			LocationCache locCache = (LocationCache) MXServer.getMXServer().getFromMaximoCache(LocationCache.CACHE_NAME);
			if (locCache.isLocationExist(this.getMboValue().getCurrentValue().asString())) {
				throw new MXApplicationException("custom","ItemDescSameAsLocationDesc");
			}
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		

	}
}
