package com.anupamt.products_customer_simulator.service;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.anupamt.configurations.ConfigurationConstants;

@Service
public class GraphQLService {

	private static GraphQLService instance_;
	private final HttpGraphQlClient graphQlClient;
	
	private GraphQLService() {
		WebClient webClient = WebClient.builder()
				.baseUrl(ConfigurationConstants.GRAPHQL_URL)
				.build();
		graphQlClient = HttpGraphQlClient.builder(webClient).build();
	}
	
	public static GraphQLService getInstance() {
		if (instance_ == null) {
	        synchronized (GraphQLService.class) {
	            if (instance_ == null) {
	            	instance_ = new GraphQLService();
	            }
	        }
	    }
	    return instance_;
	}

	public HttpGraphQlClient getGraphQlClient() {
		return graphQlClient;
	}
}
