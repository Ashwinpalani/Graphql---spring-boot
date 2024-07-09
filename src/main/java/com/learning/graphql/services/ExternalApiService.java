package com.learning.graphql.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.graphql.domain.Employee;

@Service
public class ExternalApiService {
	
	@Autowired
	private Environment environment;
	
    private static final String API_URL = "https://d086b97ec673489a883b3414cf424ebc.api.mockbin.io/";

    public List<Employee> fetchExternalData() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("Mock API  ===> " + environment.getProperty("external.api"));
        HttpGet request = new HttpGet(API_URL);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = null;
				try {
					result = EntityUtils.toString(entity);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
                ObjectMapper mapper = new ObjectMapper();
                List<Employee> employeeList =  Arrays.asList(mapper.readValue(result, Employee[].class));
                return employeeList;
            }
        }
        return Collections.emptyList();
    }
    
    
    public Employee createNew(Employee employee) throws IOException, ParseException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost request = new HttpPost(API_URL);
    	try(CloseableHttpResponse response = httpClient.execute(request)){
    		HttpEntity entity = response.getEntity();
    		if(entity != null) {
    			 String result = null;
    			 try {
    				 result = EntityUtils.toString(entity);
    			 }
    			 catch (IOException e) {
 					e.printStackTrace();
 				}
    			   ObjectMapper mapper = new ObjectMapper();
                   Employee employeeData =  mapper.readValue(result, Employee.class);
                   return employeeData;
    		}
    	}
    	return null;
    }
}

