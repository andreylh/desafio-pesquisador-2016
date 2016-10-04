package com.andreylh.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class ClientMqtt implements Client, MqttCallback {

	private static final String TOPIC = "research/mqtt";
	private MqttClient mqttClient;
	private MqttMessage message;
	private int qos;

	ClientMqtt(String serverUrl, int qos) {
		try {			
			mqttClient = new MqttClient(serverUrl, MqttClient.generateClientId());
			mqttClient.setCallback(this);
			mqttClient.connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		this.qos = qos;
	}

	@Override
	public void release() {
		try {
			mqttClient.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(String message) {
		getMessage().setPayload(message.getBytes());
		try {
			mqttClient.publish(TOPIC, getMessage());
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private MqttMessage getMessage() {
		if (message == null) {
			message = new MqttMessage();
			message.setQos(qos);
		}
		return message;
	}

	@Override
	public void connectionLost(Throwable cause) {
		cause.printStackTrace();
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("Delivery Complete: " + token.getMessageId());
	}
}
