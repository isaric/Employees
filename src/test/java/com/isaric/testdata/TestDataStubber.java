package com.isaric.testdata;

import java.util.ArrayList;
import java.util.List;

import com.isaric.forms.UserCreateForm;
import com.isaric.model.Employee;

public class TestDataStubber {
	
	public static List<Employee> getSampleEmployees(){
		ArrayList<Employee> sampleEmployees = new ArrayList<>();
		Employee employee1 = new Employee();
		employee1.setName("John");
		employee1.setSurname("Smith");
		employee1.setDepartment("Maintanence");
		employee1.setAge(39);
		sampleEmployees.add(employee1);
		Employee employee2 = new Employee();
		employee2.setName("Jane");
		employee2.setSurname("Johnson");
		employee2.setDepartment("Accounting");
		employee2.setAge(28);
		sampleEmployees.add(employee2);
		Employee employee3 = new Employee();
		employee3.setName("Marky");
		employee3.setSurname("Mark");
		employee3.setDepartment("Public Relations");
		employee3.setAge(31);
		sampleEmployees.add(employee3);
		return sampleEmployees;
	}
	public static List<UserCreateForm> getSampleUserForms(){
		ArrayList<UserCreateForm> sampleUsers = new ArrayList<>();
		UserCreateForm form1 = new UserCreateForm();
		UserCreateForm form2 = new UserCreateForm();
		UserCreateForm form3 = new UserCreateForm();
		String password = "password";
		form1.setEmail("test1@test.com");
		form1.setPassword(password);
		form2.setEmail("test2@test.com");
		form2.setPassword(password);
		form3.setEmail("test3@test.com");
		form3.setPassword(password);
		sampleUsers.add(form1);
		sampleUsers.add(form2);
		sampleUsers.add(form3);
		return sampleUsers;
	}
	
}
