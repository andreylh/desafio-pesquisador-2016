package com.andreylh.client;

public class ClientFactory {

	private static final String MQTT = "tcp";
	private static final String COAP = "coap";
	
	public static Client getClient(String serverUrl, int qos) {
		String url = serverUrl.toLowerCase();
		if (url.startsWith(MQTT)) {
			return new ClientMqtt(url, qos);
		} else if (url.startsWith(COAP)) {
			return new ClientCoap(url);
		}
		
		throw new IllegalArgumentException("Invalid protocol.");
	}
	
}
