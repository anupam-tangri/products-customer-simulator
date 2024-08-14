package com.anupamt.products_customer_simulator.graphqloperation.queries;

import java.util.List;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.models.Sale;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductGQLs;

public class GetAllSalesQuery implements GraphqlOperation<List<Sale>> {


	public GetAllSalesQuery(){
	}
	
	@Override
	public List<Sale> execute() {
		return GraphQLService.getInstance().getGraphQlClient().document(ProductGQLs.ALL_SALES_QUERY
				).retrieveSync("sales").toEntityList(Sale.class);
	}

}
