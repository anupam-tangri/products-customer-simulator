package com.anupamt.products_customer_simulator.service;

public class ProductGQLs {
	public static final String ALL_PRODUCTS_QUERY = """
			query AllProducts {
			  products {
			    id
			    title
			    price
			  }
			}
			""";
	public static final String ALL_SALES_QUERY = """
			query ExampleSalesQuery {
			  sales {
			    id
			    returned
			    product {
			      id
			      title
			      price
			    }
			  }
			}
			""";
	public static final String ADD_SALES_MUTATION = """
			mutation AddSale($productId: String!) {
			  addSale(product_id: $productId) {
			    id
			  }
			}
			""";
	
	public static final String RETURN_SALE_MUTATION = """
			mutation AddReturn($id: String!) {
			  addReturn(id: $id) {
			    id
			    returned
			  }
			}
			""";
	public static final String ADD_REVIEW_MUTATION = """
			mutation AddReview($rating: Int!, $productId: String!) {
			  addReview(rating: $rating, product_id: $productId) {
			    id
			    rating
			  }
			}
			""";
}
