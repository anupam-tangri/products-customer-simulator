package com.anupamt.products_customer_simulator.graphqloperation.mutations;

import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.models.Sale;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductGQLs;

public class ReturnSaleMutation implements GraphqlOperation<Void> {

	private final Sale returnSale;
	
	public ReturnSaleMutation(Sale returnSale) {
		super();
		this.returnSale = returnSale;
	}

	@Override
	public Void execute() {
		/*
		 * mutation AddReturn($id: String!) {
			  addReturn(id: $id) {
			    id
			    returned
			}
		 */
		GraphQLService.getInstance().getGraphQlClient().document(ProductGQLs.RETURN_SALE_MUTATION).variable(
				"id", returnSale.id()).executeSync();
		return null; /*because of Void return type*/
	}

}
