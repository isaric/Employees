package com.isaric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isaric.model.Department;
import com.isaric.model.Employee;
import com.isaric.services.DepartmentService;
import com.isaric.services.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("/")
	public String welcome(){
		return "welcome";
	}
	@RequestMapping("/list")
	public String list(Model model){
		List<Employee> employees = employeeService.findAllEmployees();
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
		employeeService.saveEmployee(e);
		return "welcome";
	}
	@RequestMapping("/departments")
	public String departments(Model model){
		List<Department> departmentlist = departmentService.findAllDepartments();
		model.addAttribute("departments", departmentlist);
		return "departments";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Employee transfer, Model model){
		model.addAttribute("transfer", transfer);
		return "update";
	}
	@RequestMapping("/updateservice")
	public String updateService(@ModelAttribute("transfer") Employee employee){
		employeeService.saveEmployee(employee);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute Employee employee){
		employeeService.deleteEmployee(employee);
		return "redirect:list";
	}
}
