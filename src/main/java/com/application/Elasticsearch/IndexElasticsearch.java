package com.application.Elasticsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.application.vo.Bugs;
import com.application.vo.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexElasticsearch {
	final static String devicesPath = "D:/WPI/data_files/devices.csv";
	final static String testersPath = "D:/WPI/data_files/testers.csv";
	final static String bugsPath = "D:/WPI/data_files/bugs.csv";
	final static ArrayList<Bugs> bugs = new ArrayList<Bugs>();
	final static ArrayList<Document> DOCUMENTS = new ArrayList<Document>();
	final static ArrayList<String> deviceDetails = new ArrayList<String>();
	final static ArrayList<String> testerDetails = new ArrayList<String>();

	public void indexDevices(ArrayList<Document> documents) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		/*String devicesJSON = mapper.writeValueAsString(documents);
		System.out.println(devicesJSON);*/
		/*
		 * HashMap<String, Object> documentDetails = new HashMap<String,
		 * Object>(); documentDetails.put("details", devicesJSON);
		 */
		TransportClient client = ElasticsearchConnect.getClient();
		for (Document document : documents) {
			String documentJSON = mapper.writeValueAsString(document);
			IndexResponse indexResponse = client.prepareIndex("details", "detail")
					.setSource(documentJSON, XContentType.JSON).get();
			
		}
		
		
	}

	private void createDeviceJSONObject() {
		Scanner scanner;
		ArrayList<Document> documents = new ArrayList<Document>();

		for (Bugs bug : bugs) {

			try {
				scanner = new Scanner(new File(devicesPath));
				String row = scanner.nextLine();

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] lineArray = line.split(",");
					if (line.contains(bug.getDeviceId())) {

						deviceDetails.add(lineArray[1]);

					}

				}

				//
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// System.out.println(deviceDetails);
	}

	private void createTesterJSONObject() {
		Scanner scanner;
		ArrayList<Document> documents = new ArrayList<Document>();

		for (Bugs bug : bugs) {

			try {
				scanner = new Scanner(new File(testersPath));
				String row = scanner.nextLine();

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] lineArray = line.split(",");
					if (line.contains(bug.getTesterId())) {

						// System.out.println();
						testerDetails.add(lineArray[3]);

					}

				}

				//
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private String createBugsJSONObject() {

		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner;
		String arraytoJson = null;
		try {
			scanner = new Scanner(new File(devicesPath));
			String row = scanner.nextLine();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

			}

			// System.out.println(arraytoJson);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arraytoJson;

	}

	private String createTesterJSON() {
		// ArrayList<Devices> devices = new ArrayList<Devices>();

		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner;
		String arraytoJson = null;
		try {
			scanner = new Scanner(new File(bugsPath));
			scanner.nextLine();
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				String[] lineArray = line.split(",");
				bugs.add(new Bugs(lineArray[0], lineArray[1], lineArray[2]));

			}

			// arraytoJson = mapper.writeValueAsString(bugs);
			System.out.println(bugs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*
			 * catch (JsonProcessingException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		return arraytoJson;

	}

	public static void main(String[] args) {
		IndexElasticsearch elasticsearch = new IndexElasticsearch();
		elasticsearch.createTesterJSON();
		elasticsearch.createDeviceJSONObject();
		elasticsearch.createTesterJSONObject();

		ArrayList<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < bugs.size(); i++) {
			documents.add(new Document(bugs.get(i).getTesterId(), testerDetails.get(i), bugs.get(i).getDeviceId(),
					deviceDetails.get(i), bugs.get(i).getBugId()));
		}

		try {
			elasticsearch.indexDevices(documents);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// elasticsearch.indexTesters();
	}

}
