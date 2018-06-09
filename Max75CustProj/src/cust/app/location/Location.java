package cust.app.location;

import java.rmi.RemoteException;

import psdi.mbo.MboSet;
import psdi.util.MXException;

public class Location extends psdi.app.location.Location implements LocationRemote {

	public Location(MboSet ms) throws MXException, RemoteException {
		super(ms);
	}

}
