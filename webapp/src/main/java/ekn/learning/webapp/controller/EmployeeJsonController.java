package ekn.learning.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.exceptions.EmployeeInvalidArgumentsException;
import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.model.Employee;
import ekn.learning.webapp.model.Faculty;
import ekn.learning.webapp.repos.EmployeeJdbcRepository;
import ekn.learning.webapp.services.EmployeeService;

@RestController
@RequestMapping(value = "/employeejson/employee") 
public class EmployeeJsonController {
	
	//@Autowired
	//private EmployeeJdbcRepository repository;
	
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
	
	
	//TODO: Verwijderen van deze functie. Is alleen voor testen
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Map getStuff() {
		Employee employee = new Employee();
		employee.setId(28);
		employee.setFirstName("testname");
		employee.setLastName("Test last name");
		employee.setEmail("email");
		Faculty faculty = new Faculty();
		faculty.setId(21);
		faculty.setName("Test faculty");
		
		Map<String, Object> map = new HashMap<>();
		map.put("employee", employee);
		map.put("faculty", faculty);
		return map;
		
		//ExceptionHandlingController exception = new ExceptionHandlingController();
		//throw exception.handleError("/test", Exception.);
	
		
	}
	
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String doError() {
		//throw new EmployeeNotFoundException(1);
		throw new EmployeeInvalidArgumentsException();
	}
	
}
