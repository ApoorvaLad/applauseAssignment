package com.application.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticsearchConnect {

	final static Logger LOGGER = Logger.getLogger(ElasticsearchConnect.class);
	public static void main(String[] args) {
		System.out.println("Here");
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		try {
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
			LOGGER.debug("Connnected");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
