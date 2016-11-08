package com.isaric.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "employees")
@Component
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private int age;
	@Column
	private String department;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public boolean equals(Object object){
		if (!(object instanceof Employee)) return false;
		Employee emp = (Employee) object;
		if ((emp.getId() != 0L) && (this.getId() != 0L)) return emp.getId() == this.getId();
		if (!emp.getName().equals(this.getName())) return false;
		if (!emp.getSurname().equals(this.getSurname())) return false;
		if (!emp.getDepartment().equals(this.getDepartment())) return false;
		if (emp.getAge() != this.getAge()) return false;
		return true;
	}
}
