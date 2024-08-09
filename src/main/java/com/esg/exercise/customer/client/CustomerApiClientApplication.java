package com.esg.exercise.customer.client;

import com.esg.exercise.customer.client.config.ApiProxyProperties;
import com.esg.exercise.customer.client.model.Customer;
import com.esg.exercise.customer.client.util.CsvFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A console based API client which reads data from a specified location or default location (data)
 * Transforms the CSV file to customer information and invokes an API to save the data in a database.
 */
@SpringBootApplication
@EnableConfigurationProperties(value = ApiProxyProperties.class)
public class CustomerApiClientApplication implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger(CustomerApiClientApplication.class);
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ApiProxyProperties apiProxyProperties;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.warn("No input file specified - defaulting to data/customer.csv");
		String sourceFile = "data\\customer.csv";

		if (Objects.nonNull(args) && args.length > 0) {
			sourceFile = args[0];
		}

		log.info(sourceFile);

		CsvFileReader csvFileReader = new CsvFileReader();
		List<Customer> customers = csvFileReader.readFile(sourceFile);

		customers.forEach(customer -> {
			ResponseEntity<Customer> savedCustomer = restTemplate.
					postForEntity(apiProxyProperties.getUrl(), customer, Customer.class);

			log.info(savedCustomer.toString());
		});
	}
}
