package com.isaric.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.isaric.model.Employee;
import com.isaric.repositories.EmployeeRepository;
import com.isaric.testdata.TestDataStubber;


public class EmployeeServiceMockTest {
	
	private static EmployeeServiceImpl employeeService;
	private static EmployeeRepository repository;
	private static List<Employee> allEmployees;
	
	@BeforeClass
	public static void setupClass(){
		employeeService = new EmployeeServiceImpl();
		repository = Mockito.mock(EmployeeRepository.class);
		allEmployees = TestDataStubber.getSampleEmployees();
		Mockito.when(repository.findAll()).thenReturn(allEmployees);
		employeeService.setRepository(repository);
	}
	
	@Test
	public void addEmployeeSuccessfulTest(){
		Employee emp = new Employee();
		emp.setAge(25);
		emp.setDepartment("Human Resources");
		emp.setName("Carina");
		emp.setSurname("Ohrender");
		Mockito.when(repository.save(emp)).thenAnswer(addToListAndReturnEmployee(emp,allEmployees));
		employeeService.saveEmployee(emp);
		Mockito.verify(repository).save(emp);
		List<Employee> returnedEmployees = employeeService.findAllEmployees();
		assertTrue(returnedEmployees.contains(emp));
		allEmployees.remove(emp);
	}
	
	@Test
	public void findAllEmployeeSuccessfullTest(){
		List<Employee> returnedAllEmployees = employeeService.findAllEmployees();
		assertEquals(3,returnedAllEmployees.size());
		assertTrue(allEmployees.containsAll(returnedAllEmployees));
	}
	private Answer<Employee> addToListAndReturnEmployee(Employee emp, List<Employee> list){
		return new Answer<Employee>(){
			public Employee answer(InvocationOnMock invocation){
				list.add(emp);
				return emp;
			}
		};
	}
}
