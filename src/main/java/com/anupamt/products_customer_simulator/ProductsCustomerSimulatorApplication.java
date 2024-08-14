package com.anupamt.products_customer_simulator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.anupamt.products_customer_simulator.service.GraphQLService;
import com.anupamt.products_customer_simulator.service.ProductsCustomerSimulatorService;

@SpringBootApplication
public class ProductsCustomerSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsCustomerSimulatorApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(GraphQLService service) {
		return args -> {
			ProductsCustomerSimulatorService simulatorService = new ProductsCustomerSimulatorService();
			simulatorService.simulateProductOperations();
		};
	}
}
