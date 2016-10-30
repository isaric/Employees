package com.isaric.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaric.model.Employee;
import com.isaric.repositories.EmployeeRepository;
import com.isaric.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
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
	@Override
	public Employee findEmployeeById(long id) {
		return repository.findOne(id);
	}
}
