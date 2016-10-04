package com.andreylh.main;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.andreylh.client.Client;
import com.andreylh.client.ClientFactory;
import com.andreylh.data.DataProvider;

public class Main {

	private static String serverUrl;
	private static int dataLimit;
	private static int qos;

	public static void main(String[] args) throws InterruptedException {
		parseArguments(args);

		DataProvider dataProvider = new DataProvider(Paths.get(".\\src\\main\\resources\\data"));
		List<String> data = dataProvider.getData(dataLimit);

		Client client = ClientFactory.getClient(serverUrl, qos);
		try {
			for (String d : data) {
				client.sendMessage(d);
				TimeUnit.MILLISECONDS.sleep(300);
			}
		} finally {
			client.release();
		}
	}

	private static void parseArguments(String[] args) {
		if (args == null || args.length < 3) {
			throw new IllegalArgumentException("Your forgot to inform the server URL, number of data to send and/or QoS!");
		}

		serverUrl = args[0];
		dataLimit = Integer.valueOf(args[1]);
		qos = Integer.valueOf(args[2]);
	}
}
