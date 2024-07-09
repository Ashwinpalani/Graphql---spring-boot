package com.learning.graphql.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.learning.graphql.domain.Employee;
import com.learning.graphql.services.EmployeeService;
import com.learning.graphql.services.ExternalApiService;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Configuration
public class GraphQLSchemaConfig {

    @Autowired
    private EmployeeService employeeService;

    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        Resource resource = new ClassPathResource("graphql/schema.graphqls");
        Reader reader = new InputStreamReader(resource.getInputStream());
        String sdl = FileCopyUtils.copyToString(reader);

        TypeDefinitionRegistry typeRegistry = schemaParser.parse(sdl);
        RuntimeWiring wiring = buildRuntimeWiring();
        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("getAllEmployees", environment -> {
                            List<Employee> employees = employeeService.getAllEmployees();
                            if (employees.isEmpty()) {
                                throw new RuntimeException("No employees found");
                            }
                            return employees;
                        })
                        .dataFetcher("getEmployeeById", environment -> {
                            String id = environment.getArgument("id");
                            return employeeService.getEmployeeById(Long.parseLong(id))
                                    .orElseThrow(() -> new RuntimeException("Employee not found"));
                        }))
                .build();
    }
}
