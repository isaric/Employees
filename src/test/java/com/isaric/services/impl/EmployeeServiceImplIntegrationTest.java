package com.isaric.services.impl;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.isaric.EmployeesApplication;
import com.isaric.model.Employee;
import com.isaric.testdata.TestDataStubber;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmployeesApplication.class)
@TestPropertySource(locations="classpath:test.properties")
@WebIntegrationTest
public class EmployeeServiceImplIntegrationTest {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	private static List<Employee> sampleEmployees = TestDataStubber.getSampleEmployees();
	
	@Before
	public void setup(){
		for (Employee emp : sampleEmployees){
			employeeService.saveEmployee(emp);
		}
	}
	
	@After
	public void teardown(){
		for (Employee emp: sampleEmployees){
			employeeService.deleteEmployee(emp);
		}
	}
	
	@Test
	public void addEmployeeSuccessTest(){
		Employee emp = new Employee();
		emp.setAge(25);
		emp.setDepartment("Operations");
		emp.setName("Wayne");
		emp.setSurname("World");
		employeeService.saveEmployee(emp);
		List<Employee> allEmployees = employeeService.findAllEmployees();
		assertEquals(sampleEmployees.size() + 1, allEmployees.size());
		assertTrue(allEmployees.contains(emp));
		employeeService.deleteEmployee(emp);
	}
	
	@Test
	public void getAllEmployeesSuccess(){
		List<Employee> allEmployees = employeeService.findAllEmployees();
		assertEquals(sampleEmployees.size(),allEmployees.size());
		assertTrue(allEmployees.containsAll(sampleEmployees));
	}
}
