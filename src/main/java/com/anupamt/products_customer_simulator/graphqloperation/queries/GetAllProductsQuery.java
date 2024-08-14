package com.anupamt.products_customer_simulator.graphqloperation.queries;

import java.util.List;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.models.Product;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductGQLs;

public class GetAllProductsQuery implements GraphqlOperation<List<Product>> {
	

	public GetAllProductsQuery(){
	}
	
	@Override
	public List<Product> execute() {
		return GraphQLService.getInstance().getGraphQlClient().document(ProductGQLs.ALL_PRODUCTS_QUERY
				).retrieveSync("products").toEntityList(Product.class);
	}

}
