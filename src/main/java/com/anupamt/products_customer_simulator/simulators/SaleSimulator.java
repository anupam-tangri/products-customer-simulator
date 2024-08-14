package com.anupamt.products_customer_simulator.simulators;

import static com.anupamt.cache.ProductsCache.getInstance;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.graphqloperation.mutations.AddSaleMutation;

public class SaleSimulator implements Runnable {

	private final BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue;
    private boolean running = false; 
    
    public SaleSimulator(BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue) {
        this.graphqlOperationQueue = graphqlOperationQueue;
    }

    @Override
    public void run() {
        running = true;
        generateAndPublishSales();
    }
    
    private void generateAndPublishSales() {
        while (running) {
            try {
            	Thread.sleep(10000);
            	GraphqlOperation<Void> sale = generateSale();
            	graphqlOperationQueue.put(sale);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    
    private GraphqlOperation<Void> generateSale() {
    	/*Take a Random product and generate a sale*/
    	return new AddSaleMutation(getInstance().getAllProducts().get(
    			new Random().ints(0,getInstance().getAllProducts().size()).findAny().getAsInt()));
	}

	public void stop() {
        running = false;
    }
}
