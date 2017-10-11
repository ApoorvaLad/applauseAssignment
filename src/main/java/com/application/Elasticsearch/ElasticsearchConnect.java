package com.application.Elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticsearchConnect {
	public static TransportClient client;

	public static TransportClient getClient() {
		if (client == null) {
			ElasticsearchConnect connect = new ElasticsearchConnect();
			connect.createConnection();
		}
		return client;
	}

	private void createConnection() {

		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		client = new PreBuiltTransportClient(settings);
		try {

			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	final static Logger LOGGER = Logger.getLogger(ElasticsearchConnect.class);

	public static void main(String[] args) {
		getClient();
	}
}
