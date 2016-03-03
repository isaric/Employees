package com.isaric.model;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{
	
	@Query(value= "SELECT department as name, COUNT(department)" 
	    + " FROM Employees GROUP BY department", nativeQuery = true)
	public List<Object[]> listDepartments();
	
	@Override
	public List<Employee> findAll();
}
