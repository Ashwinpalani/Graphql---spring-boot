package com.learning.graphql.domain;

public class EmployeeInput {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String department;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public EmployeeInput(Long id, String firstName, String lastName, String email, String departmentId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
	}
	
	public EmployeeInput( String firstName, String lastName, String email, String departmentId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
	}

	public EmployeeInput() {
		super();
	}

	@Override
	public String toString() {
		return "EmployeeInput [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", departmentId=" + department + "]";
	}

	
}
