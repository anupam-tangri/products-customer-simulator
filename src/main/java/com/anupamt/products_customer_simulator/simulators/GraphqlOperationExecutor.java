package com.anupamt.products_customer_simulator.simulators;

import java.util.concurrent.BlockingQueue;
import com.anupamt.products_customer_simulator.graphqloperation.GraphqlOperation;

public class GraphqlOperationExecutor implements Runnable {
	private final BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue;
	private boolean running = false;
	
	public GraphqlOperationExecutor(BlockingQueue<GraphqlOperation<?>> graphqlOperationQueue) {
		this.graphqlOperationQueue = graphqlOperationQueue;
	}
	
	@Override
    public void run() {
        running = true;
        while (running) {
            try {
            	graphqlOperationQueue.take().execute();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
	
	public void stop() {
        running = false;
    }
}
