package com.application.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;

import com.application.Elasticsearch.ElasticsearchConnect;

public class SearchDao implements SearchDaoImpl {

	public void searchDevices(ArrayList<String> devices, ArrayList<String> countries)
			throws IOException, InterruptedException, ExecutionException {
		List<Integer> testerIds = new ArrayList<Integer>();
		TransportClient client = ElasticsearchConnect.getClient();
		QueryBuilder builder = QueryBuilders.termsQuery("country", countries);
		QueryBuilder builder2 = QueryBuilders.termsQuery("deviceName", devices);
		final BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
		booleanQueryBuilder.must(builder2);
		booleanQueryBuilder.must(builder);
		AggregationBuilder aggregation = AggregationBuilders.cardinality("distintCount").field("testerId");
		AggregationBuilder aggregationBuilder = AggregationBuilders.terms("testerValues").field("testerId");
		SearchResponse response = client.prepareSearch("details").setSize(1000).setQuery(booleanQueryBuilder)
				.addAggregation(aggregation).addAggregation(aggregationBuilder).execute().get();
		InternalCardinality internalCardinality = response.getAggregations().get("distintCount");
		Terms terms = response.getAggregations().get("testerValues");
		Collection<Terms.Bucket> buckets = (Collection<Bucket>) terms.getBuckets();
		for (Bucket bucket : buckets) {
			int i = Integer.parseInt(bucket.getKeyAsString());
			testerIds.add(i);

		}
		for (Integer testerId : testerIds) {
			for (String device : devices) {
				QueryBuilder innerbuilder = QueryBuilders.termQuery("testerId", testerId);
				QueryBuilder innerbuilder1 = QueryBuilders.termQuery("deviceName", device);
				final BoolQueryBuilder booleaninnerQueryBuilder = QueryBuilders.boolQuery();
				booleaninnerQueryBuilder.must(innerbuilder);
				booleaninnerQueryBuilder.must(innerbuilder1);
				AggregationBuilder innerAggregation = AggregationBuilders.cardinality("bugCount").field("bugId");
				response = client.prepareSearch("details").setSize(1000).setQuery(booleaninnerQueryBuilder)
						.addAggregation(innerAggregation).execute().get();
				InternalCardinality internalCardinality1 = response.getAggregations().get("bugCount");
				System.out.println("For device " + device + " bug count by tester" + testerId + "is "
						+ internalCardinality1.getValue());
			}

		}

	}

	public static void main(String[] args) {
		SearchDao dao = new SearchDao();
		ArrayList<String> countries = new ArrayList<String>();
		countries.add("\"US\"");
		countries.add("\"GB\"");

		ArrayList<String> devices = new ArrayList<String>();
		devices.add("\"iPhone 4S\"");

		try {
			dao.searchDevices(devices, countries);
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
	}

}
