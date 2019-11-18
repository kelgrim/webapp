package ekn.learning.webapp.controller;

//Top level path = /api/v1

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ekn.learning.webapp.exceptions.EmployeeInvalidArgumentsException;
import ekn.learning.webapp.model.Employee;


import ekn.learning.webapp.services.EmployeeService;

@RestController
@RequestMapping(value = "/employee") 
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> getEmployees(){
		return service.getEmployees();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable() Integer id) {
			  return service.getEmployee(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public int addEmployee(@RequestBody() Employee employee) {
		return service.addEmployee(employee);	
	}

}
