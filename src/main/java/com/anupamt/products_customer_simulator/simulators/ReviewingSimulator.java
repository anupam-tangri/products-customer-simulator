package com.anupamt.products_customer_simulator.simulators;

import static com.anupamt.cache.ProductsCache.getInstance;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.graphqloperation.mutations.AddReviewMutation;
import com.anupamt.products_customer_simulator.models.Product;

public class ReviewingSimulator implements Runnable {

	private final BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue;
    private boolean running = false; 
    
    public ReviewingSimulator(BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue) {
        this.graphqlOperationQueue = graphqlOperationQueue;
    }

    @Override
    public void run() {
        running = true;
        generateAndPublishReview();
    }
    
    private void generateAndPublishReview() {
        while (running) {
            try {
            	Thread.sleep(10000);
            	GraphqlOperation<Void> sale = generateReview();
            	graphqlOperationQueue.put(sale);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    
    private GraphqlOperation<Void> generateReview() {
    	/*Take a Random product and generate a sale*/
    	Random randomNumberGenerator = new Random();
    	int randomRating=randomNumberGenerator.ints(1,5).findAny().getAsInt();
    	Product productToReview = getInstance().getAllProducts().get(
    			randomNumberGenerator.ints(0,getInstance().getAllProducts().size()).findAny().getAsInt());
    	return new AddReviewMutation(productToReview, randomRating);
	}

	public void stop() {
        running = false;
    }
}