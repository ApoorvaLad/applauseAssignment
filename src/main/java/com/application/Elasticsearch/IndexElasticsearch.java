package com.application.Elasticsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
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

	public void indexDevices(ArrayList<Document> documents) throws IOException {
		TransportClient client = ElasticsearchConnect.getClient();
		final IndicesExistsResponse res = client.admin().indices().prepareExists("details").execute().actionGet();
		if (res.isExists()) {
			final DeleteIndexRequestBuilder delIdx = client.admin().indices().prepareDelete("details");
			delIdx.execute().actionGet();
		}

		final CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate("details");

		// MAPPING GOES HERE
		HashMap<String, HashMap<String, String>> mappingFields = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> countryMap = new HashMap<String, String>();
		HashMap<String, String> deviceNameMap = new HashMap<String, String>();
		countryMap.put("type", "keyword");
		deviceNameMap.put("type", "keyword");
		mappingFields.put("deviceName", deviceNameMap);
		mappingFields.put("country", countryMap);

		final XContentBuilder mappingBuilder = jsonBuilder().startObject().startObject("detail").startObject("_all")
				.field("enabled", "false").endObject().startObject("properties").startObject("deviceName")
				.field("type", "keyword").endObject().startObject("country").field("type", "keyword").endObject()
				.startObject("testerId").field("type", "integer").endObject().startObject("bugId")
				.field("type", "integer").endObject().endObject().endObject().endObject();
		// .startObject("properties");
		System.out.println(mappingBuilder.string());
		createIndexRequestBuilder.addMapping("detail", mappingBuilder);

		// MAPPING DONE
		createIndexRequestBuilder.execute().actionGet();

		// Add documents
		final IndexRequestBuilder indexRequestBuilder = client.prepareIndex("details", "detail");
		// build json object
		ObjectMapper mapper = new ObjectMapper();

		for (Document document : documents) {
			String documentJSON = mapper.writeValueAsString(document);
			indexRequestBuilder.setSource(documentJSON, XContentType.JSON).execute().actionGet();
		}
		// contentBuilder.field("details", value);

		// indexRequestBuilder.setSource(documents);
		// indexRequestBuilder.setSource(contentBuilder);
		// indexRequestBuilder.execute().actionGet();

		/*
		 * ObjectMapper mapper = new ObjectMapper(); TransportClient client =
		 * ElasticsearchConnect.getClient();
		 * 
		 * for (Document document : documents) { String documentJSON =
		 * mapper.writeValueAsString(document); IndexResponse indexResponse =
		 * client.prepareIndex("details", "detail") .setSource(documentJSON,
		 * XContentType.JSON).get();
		 * 
		 * }
		 */

	}

	private void createDeviceJSONObject() {
		Scanner scanner;

		for (Bugs bug : bugs) {

			try {
				scanner = new Scanner(new File(devicesPath));
				String row = scanner.nextLine();

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] lineArray = line.split(",");
					lineArray[0] = lineArray[0].replaceAll("^\"|\"$", "");
					String deviceId = bug.getDeviceId().replaceAll("^\"|\"$", "");
					if (lineArray[0].equals(deviceId)) {
						System.out.println(lineArray[1]);
						deviceDetails.add(lineArray[1]);

					}

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void createTesterJSONObject() {
		Scanner scanner;

		for (Bugs bug : bugs) {

			try {
				scanner = new Scanner(new File(testersPath));
				String row = scanner.nextLine();

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] lineArray = line.split(",");
					lineArray[0] = lineArray[0].replaceAll("^\"|\"$", "");
					if (lineArray[0].equals(Integer.toString(bug.getTesterId()))) {

						System.out.println(lineArray[3]);
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
		Scanner scanner;
		String arraytoJson = null;
		try {
			scanner = new Scanner(new File(bugsPath));
			scanner.nextLine();
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				String[] lineArray = line.split(",");
				lineArray[2] = lineArray[2].replaceAll("^\"|\"$", "");
				lineArray[0] = lineArray[0].replaceAll("^\"|\"$", "");
				System.out.println(lineArray[2]);
				System.out.println(Integer.parseInt(lineArray[2]));
				bugs.add(new Bugs(Integer.parseInt(lineArray[0]), lineArray[1], Integer.parseInt(lineArray[2])));

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arraytoJson;

	}

	public static void main(String[] args) {
		IndexElasticsearch elasticsearch = new IndexElasticsearch();
		elasticsearch.createBugsJSONObject();
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
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
