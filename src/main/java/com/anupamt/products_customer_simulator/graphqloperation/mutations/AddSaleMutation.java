package com.anupamt.products_customer_simulator.graphqloperation.mutations;

import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.models.Product;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductGQLs;

public class AddSaleMutation implements GraphqlOperation<Void> {

	private final Product productSold;
	
	public AddSaleMutation(Product productSold) {
		super();
		this.productSold = productSold;
	}

	@Override
	public Void execute() {
		/*
		 * mutation AddSale($productId: String!) {
			  addSale(product_id: $productId) {
			    id
			  }
			}
		 */
		GraphQLService.getInstance().getGraphQlClient().document(ProductGQLs.ADD_SALES_MUTATION).variable(
				"productId", productSold.id()).executeSync();
		return null; /*because of Void return type*/
	}

}
