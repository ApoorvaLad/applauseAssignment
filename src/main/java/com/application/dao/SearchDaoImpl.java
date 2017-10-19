package com.application.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.application.vo.OutputVo;

public interface SearchDaoImpl {
	public List<OutputVo> searchDevices(ArrayList<String> devices,ArrayList<String> countries) throws IOException, InterruptedException, ExecutionException;
}
