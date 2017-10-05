package com.application.dao;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class SearchDAO implements SearchDAOImpl {

	private static final String INDEX_DIR = "C:/Uers/alad/luceneindex";

	public void indexDevices() {
		// TODO Auto-generated method stub

	}

	public void indexTesters() {
		// TODO Auto-generated method stub

	}

	public void indexBugDetails() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		FSDirectory dir;
		try {
			dir = FSDirectory.open(Paths.get(INDEX_DIR));
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			IndexWriter writer = new IndexWriter(dir, config);
			List<Document> documents = new ArrayList<Document>();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
