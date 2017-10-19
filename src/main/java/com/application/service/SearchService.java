package com.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.application.Elasticsearch.ElasticsearchConnect;
import com.application.Elasticsearch.IndexElasticsearch;
import com.application.dao.SearchDao;
import com.application.vo.Devices;
import com.application.vo.OutputVo;
import com.google.gson.Gson;

@Service
public class SearchService implements SearchServiceImpl {

	public Set<String> getAllDevices() {
		Scanner scanner;
		Set<String> devices = new HashSet<String>();
		try {
			scanner = new Scanner(new File("D:/WPI/data_files/devices.csv"));
			String row = scanner.nextLine();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineArray = line.split(",");

				devices.add(lineArray[1]);
			}

			//
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(devices);
		return devices;
	}

	public Set<String> getCountryNames() {
		Scanner scanner;
		Set<String> countries = new HashSet<String>();
		try {
			scanner = new Scanner(new File("D:/WPI/data_files/testers.csv"));
			String row = scanner.nextLine();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineArray = line.split(",");

				countries.add(lineArray[3]);
			}

			//
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(countries);
		return countries;
	}

	public List<OutputVo> searchByDevice(HashMap<Object, Object> requestbody) {
		ArrayList<String> devices = (ArrayList<String>) requestbody.get("device");
		ArrayList<String> countries = (ArrayList<String>) requestbody.get("country");
		SearchDao dao = new SearchDao();
		List<OutputVo> list = new ArrayList<OutputVo>();
		try {
			list = dao.searchDevices(devices, countries);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public static void main(String[] args) {
		SearchService searchService = new SearchService();
		searchService.getAllDevices();

	}

}
