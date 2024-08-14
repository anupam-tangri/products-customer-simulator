package com.anupamt.products_customer_simulator.graphqloperation.mutations;

import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.models.Product;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductGQLs;

public class AddReviewMutation implements GraphqlOperation<Void> {

	private final Product productRated;
	private final int productRating;
	
	public AddReviewMutation(Product productRated, int productRating) {
		super();
		this.productRated = productRated;
		this.productRating = productRating;
	}

	@Override
	public Void execute() {
		/*
		 * mutation AddReview($rating: Int!, $productId: String!) {
			  addReview(rating: $rating, product_id: $productId) {
			    id
			    rating
			  }
			}
		 */
		GraphQLService.getInstance().getGraphQlClient().document(ProductGQLs.ADD_REVIEW_MUTATION).variable(
				"rating", productRating).variable(
				"productId", productRated.id()).executeSync();
		return null; /*because of Void return type*/
	}

}
