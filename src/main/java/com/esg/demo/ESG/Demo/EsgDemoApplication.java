package com.esg.demo.ESG.Demo;

import com.esg.demo.ESG.Demo.csvutil.CsvUtils;
import com.esg.demo.ESG.Demo.customer.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class EsgDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(EsgDemoApplication.class, args);
		CsvUtils csvUtils = new CsvUtils();
		List<Customer> customers = csvUtils.getDataFromCsv("./src/main/resources/csv/UserData.csv");
		csvUtils.sendDataToAPI(customers);
	}

}
