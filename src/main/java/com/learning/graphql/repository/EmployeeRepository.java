package com.learning.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.graphql.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{


}

