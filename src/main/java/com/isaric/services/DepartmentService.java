package com.isaric.services;

import java.util.List;

import com.isaric.model.Department;

public interface DepartmentService {
	
	public List<Department> findAllDepartments();
	
	public Department findDepartmentByName(String name);
}
