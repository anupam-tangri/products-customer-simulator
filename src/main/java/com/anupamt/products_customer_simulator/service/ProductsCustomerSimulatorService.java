package com.anupamt.products_customer_simulator.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;
import com.anupamt.products_customer_simulator.simulators.GraphqlOperationExecutor;
import com.anupamt.products_customer_simulator.simulators.ReturnSimulator;
import com.anupamt.products_customer_simulator.simulators.ReviewingSimulator;
import com.anupamt.products_customer_simulator.simulators.SaleSimulator;

public class ProductsCustomerSimulatorService {
	
	public void simulateProductOperations() {
		
		/*
		 * Create a blocking queue with one size and create all the threads.
		 */
		BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue = new LinkedBlockingQueue<GraphqlOperation<?>>(1);
	
		SaleSimulator saleSimulator=new SaleSimulator(graphqlOperationQueue);
		ReviewingSimulator reviewSimulator = new ReviewingSimulator(graphqlOperationQueue);
		ReturnSimulator returnSimulator = new ReturnSimulator(graphqlOperationQueue);
		GraphqlOperationExecutor operationsExecutor = new GraphqlOperationExecutor(graphqlOperationQueue);
		
		new Thread(saleSimulator).start();
		new Thread(reviewSimulator).start();
		new Thread(returnSimulator).start();
		new Thread(operationsExecutor).start();
		
		try {
			Thread.sleep(500000);
			saleSimulator.stop();
			reviewSimulator.stop();
			returnSimulator.stop();
			operationsExecutor.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
