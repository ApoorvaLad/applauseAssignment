package com.application.Elasticsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.application.vo.Devices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexElasticsearch {
	final static String devicesPath = "C:/Users/alad/Downloads/applause_assignment/applause_assignment/devices.csv";

	public void indexDevices() {
		String devicesJSON = createJsonObject();
		Map< String, Object> json = new HashMap<String, Object>();
		json.put("devices", devicesJSON);
		TransportClient client = ElasticsearchConnect.getClient();
		IndexResponse indexResponse = client.prepareIndex("devices", "device", "1")
				.setSource(json, XContentType.JSON).get();
		System.out.println(indexResponse.getIndex());

	}

	private String createJsonObject() {
		ArrayList<Devices> devices = new ArrayList<Devices>();
		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner;
		String arraytoJson = null;
		try {
			scanner = new Scanner(new File(devicesPath));
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				String[] lineArray = line.split(",");
				devices.add(new Devices(lineArray[0], lineArray[1]));

			}
			arraytoJson = mapper.writeValueAsString(devices);
			System.out.println(arraytoJson);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arraytoJson;

	}

	public static void main(String[] args) {
		IndexElasticsearch elasticsearch = new IndexElasticsearch();
		elasticsearch.indexDevices();
	}

}
