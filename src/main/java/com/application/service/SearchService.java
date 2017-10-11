package com.application.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.application.vo.Devices;

@Service
public class SearchService implements SearchServiceImpl {
	final static String devicesPath = "C:/Users/alad/Downloads/applause_assignment/applause_assignment/devices.csv";
	
	
	public void searchDevices() {
		ArrayList<Devices> devices = new ArrayList<Devices>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(devicesPath));
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				
				String[] lineArray = line.split(",");
				devices.add(new Devices(lineArray[0], lineArray[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
