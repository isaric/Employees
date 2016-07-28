package com.isaric.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaric.model.Employee;
import com.isaric.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> findAllEmployees(){
		return repository.findAll();
	}
	public void saveEmployee(Employee employee){
		repository.save(employee);
	}
	public void deleteEmployee(Employee employee) {
		repository.delete(employee);
	}
}
