package com.anupamt.products_customer_simulator.simulators;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.graphqloperation.mutations.ReturnSaleMutation;
import com.anupamt.products_customer_simulator.graphqloperation.queries.GetAllSalesQuery;
import com.anupamt.products_customer_simulator.models.Sale;

public class ReturnSimulator implements Runnable {

	private final BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue;
    private boolean running = false; 
    
    public ReturnSimulator(BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue) {
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
    	/*Take a Random sale and return it!!*/
    	GetAllSalesQuery getAllSalesQuery= new GetAllSalesQuery();
    	List<Sale> allNonReturnedSalesList = getAllSalesQuery.execute().stream().filter(
    			sale -> sale.returned() == false).collect(Collectors.toList());
    	return new ReturnSaleMutation(allNonReturnedSalesList.get(
    			new Random().ints(0,allNonReturnedSalesList.size()).findAny().getAsInt()));
	}

	public void stop() {
        running = false;
    }
}
