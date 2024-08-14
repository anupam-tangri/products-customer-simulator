package com.anupamt.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.anupamt.products_customer_simulator.graphqloperation.queries.GetAllProductsQuery;
import com.anupamt.products_customer_simulator.models.Product;

public class ProductsCache {

		private static ProductsCache instance_;
		
		private final Map<String, Product> productsMap;
		
		private ProductsCache() {
			productsMap = new HashMap<String, Product>(
					new GetAllProductsQuery().execute().stream().collect(
					Collectors.toMap(Product::id, c -> c)));
			
		}
		
		public static ProductsCache getInstance() {
			if (instance_ == null) {
		        synchronized (ProductsCache.class) {
		            if (instance_ == null) {
		            	instance_ = new ProductsCache();
		            }
		        }
		    }
		    return instance_;
		}
		
		public List<Product> getAllProducts() {
			return new ArrayList<Product>(productsMap.values());
			
		}
}
