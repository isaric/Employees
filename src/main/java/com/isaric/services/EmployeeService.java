package com.isaric.services;

import java.util.List;

import com.isaric.model.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployees();
	
	public Employee findEmployeeById(long id);
	
	public void saveEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
	
}
