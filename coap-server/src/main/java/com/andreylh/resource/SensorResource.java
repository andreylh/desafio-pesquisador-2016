package com.andreylh.resource;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class SensorResource extends CoapResource {

	public SensorResource() {
		super("sensor");
	}
	
	@Override
	public void handlePOST(CoapExchange exchange) {
		exchange.respond(ResponseCode.CREATED);
	}
}
