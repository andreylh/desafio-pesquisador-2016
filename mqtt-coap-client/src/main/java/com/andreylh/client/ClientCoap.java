package com.andreylh.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

class ClientCoap implements Client {

	private CoapClient coapClient;

	ClientCoap(String serverUrl) {
		coapClient = new CoapClient(serverUrl);
	}

	@Override
	public void release() {
		coapClient.shutdown();
	}

	@Override
	public void sendMessage(String message) {
		CoapResponse response = coapClient.post(message, MediaTypeRegistry.TEXT_PLAIN);
		if (response != null) {
			System.out.println("Delivery Complete: " + response.getCode());
		}
	}
}
