package com.learning.graphql.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfig {

	private final GraphQLSchema graphQLSchema;

	@Autowired
	public GraphQLConfig(GraphQLSchema graphQLSchema) {
		this.graphQLSchema = graphQLSchema;
	}

	@Bean
	public GraphQL graphQL() {
		return GraphQL.newGraphQL(graphQLSchema).build();
	}
}
