package com.learning.graphql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.graphql.domain.Employee;
import com.learning.graphql.repository.EmployeeRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        logger.info("Retrieved {} employees", employees.size());
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        logger.info("Retrieved employee with ID {}: {}", id, employee.orElse(null));
        return employee;
    }
}


