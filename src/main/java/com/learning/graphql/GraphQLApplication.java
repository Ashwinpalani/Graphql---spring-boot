package com.learning.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.learning.graphql.domain.Employee;
import com.learning.graphql.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class GraphQLApplication {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GraphQLApplication.class, args);
	}
	
	@PostConstruct
	void init() {
		employeeRepository.save(new Employee(null, "Ashwin", "kumar", "asbsadjad@gmail.com", "developer"));
		employeeRepository.save(new Employee(null, "Ramesh", "kumar", "iuets@gmail.com", "tester"));
		employeeRepository.save(new Employee(null, "Arun", "kumar", "sdsdf@gmail.com", "developer"));

	}

}