package com.isaric.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaric.model.Department;
import com.isaric.repositories.EmployeeRepository;

@Service
public class DepartmentService {
	@Autowired
	private EmployeeRepository repository;
	
	public List<Department> findAllDepartments(){
		List<Object[]> departmentslist = repository.listDepartments();
		ArrayList<Department> departments = new ArrayList<>();
		for(Object[] oar: departmentslist){
			Department buffer = new Department();
			buffer.setName((String) oar[0]);
			BigInteger big = (BigInteger) oar[1];
			buffer.setNumberOfPeople(big.intValue());
			departments.add(buffer);
		}
		return departments;
	}
}
