package com.learning.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
    @Value("${getAllEmployeesQuery}")
    private String getAllEmployeesQuery;

    @Value("${getEmployeeByIdQuery}")
    private String getEmployeeByIdQuery;

    private final GraphQL graphQL;

    @Autowired
    public EmployeeController(GraphQLSchema graphQLSchema) {
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<Object> getAllEmployees() {
        String query = getAllEmployeesQuery;
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") String id) {
        String query = getEmployeeByIdQuery.replace("{id}", id);
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    public String getEmployeeByIdQuery(String id) {
        return getEmployeeByIdQuery.replace("{id}", id);
    }
    	
    
}