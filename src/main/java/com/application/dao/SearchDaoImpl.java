package com.application.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface SearchDaoImpl {
	public void searchDevices(ArrayList<String> devices,ArrayList<String> countries) throws IOException, InterruptedException, ExecutionException;
}
