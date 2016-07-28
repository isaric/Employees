package com.isaric.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isaric.model.Employee;
import com.isaric.model.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeRepository repository;
	
	@RequestMapping("/")
	public String welcome(){
		return "welcome";
	}
	@RequestMapping("/list")
	public String list(Model model){
		List<Employee> employees = repository.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("transfer", new Employee());
		return "list";
	}
	@RequestMapping("/add")
	public String add(){
		return "add";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("name") String name, @RequestParam("surname") String surname
			,@RequestParam("age") String age,@RequestParam("department") String department){
		Employee e = new Employee();
		e.setName(name);
		e.setSurname(surname);
		e.setAge(Integer.parseInt(age));
		e.setDepartment(department);
		repository.save(e);
		return "welcome";
	}
	@RequestMapping("/departments")
	public String departments(Model model){
		List<Object[]> departmentslist = repository.listDepartments();
		Map<String,BigInteger> departments = new HashMap<>();
		for(Object[] oar: departmentslist){
			departments.put((String) oar[0],(BigInteger) oar[1] );
		}
		model.addAttribute("departments", departments);
		return "departments";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Employee transfer, Model model){
		model.addAttribute("transfer", transfer);
		return "update";
	}
	@RequestMapping("/updateservice")
	public String updateService(@ModelAttribute("transfer") Employee employee){
		repository.save(employee);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute Employee employee, Model model){
		repository.delete(employee);
		return "redirect:list";
	}
}
