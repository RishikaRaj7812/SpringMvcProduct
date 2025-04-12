package com.rishika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rishika.entity.EmployeeList;
import com.rishika.model.EmployeeModel;
import com.rishika.service.EmployeeService;

@Controller
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;
	
	/*
	 * @GetMapping("/employelist") public String getEmployee() { return "employee";
	 * }
	 */
	
	
	
	
	  @GetMapping("/employelist") 
	  public String getEmployee(Model model) {
	  EmployeeList employeeList = new EmployeeList();
	  employeeList.setName("Rishika"); 
	  employeeList.setSalary(20000);
	  
	  
	  model.addAttribute("employeeList", employeeList); 
	  return "employee"; 
	  }
	 
	
	
	@GetMapping("/welcome")
	public String first() 
	{
		return "class";
	}
	
	@GetMapping("/wish")
	public String greet()
	{
		return "hello";
	}
	
	@PostMapping("/employee")
	public String getList(@ModelAttribute EmployeeModel employeeModel)
	{
		return "hello";
	}
}

