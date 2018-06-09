package cust.app.location;

import java.rmi.RemoteException;

import psdi.mbo.Mbo;
import psdi.mbo.MboServerInterface;
import psdi.mbo.MboSet;
import psdi.server.MXServer;
import psdi.txn.MXTransaction;
import psdi.util.MXException;

public class LocationSet extends psdi.app.location.LocationSet implements LocationSetRemote {

	private boolean refreshCache = false;
	
	public LocationSet(MboServerInterface ms) throws MXException, RemoteException {
		super(ms);
	}

	@Override
	protected Mbo getMboInstance(MboSet ms) throws MXException, RemoteException {
		return new Location(ms);
	}
	
	@Override
	public void saveTransaction(MXTransaction txn) throws MXException, RemoteException {
		super.saveTransaction(txn);
		
		if (toBeSaved()) {
			this.refreshCache = true;
		}
	}

	@Override
	public void fireEventsAfterDBCommit(MXTransaction txn) throws MXException, RemoteException {
		super.fireEventsAfterDBCommit(txn);
		
		if (this.refreshCache) {
			MXServer.getMXServer().reloadMaximoCache(LocationCache.CACHE_NAME, true);
		}
	}
	
	
	
}
