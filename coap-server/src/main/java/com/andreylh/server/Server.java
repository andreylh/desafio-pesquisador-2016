package com.andreylh.server;

import org.eclipse.californium.core.CoapServer;

import com.andreylh.resource.SensorResource;

public class Server {

	public static void main(String[] args) {
		CoapServer coapServer = new CoapServer();
		
		coapServer.add(new SensorResource());
		
		coapServer.start();
	}	
	
}
