package ekn.learning.webapp.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.GreetingModel;
import ekn.learning.webapp.repos.EmployeeJdbcRepository;
import ekn.learning.webapp.model.CalculatorModel;
import ekn.learning.webapp.model.DefaultModel;
import ekn.learning.webapp.model.Employee;
//TODO: Class splitsen voor de verschillende modellen. 
@RestController
public class EmployeeController {
	 @Autowired
	 EmployeeJdbcRepository repository;
	
	@RequestMapping(value= "/employee")
	public Employee getEmployee(@RequestParam(value = "id", defaultValue = "1")Integer id) {
	    
	      return repository.findById(id);
	    
	}
	
	@RequestMapping(value= "/getemployee")
	public Employee getEmployeeAlt(@RequestParam(value="id") Integer id) {
		return repository.getEmployeeAlternative(id);
	}
	
	@RequestMapping(value= "/addemployee")
	public Employee addEmployee(@RequestParam(value = "firstname") String firstName, 
			@RequestParam(value = "lastname") String lastName, 
			@RequestParam(value = "email") String email) {
		int addedId = repository.addEmployee(firstName, lastName, email);
		if (addedId >= 0) {
		 return repository.findById(addedId);
		}
		else return null;
	}
	
	@RequestMapping(value= "/getemployees")
	public List<Employee> getEmployees(){
		return repository.getEmployees();
	}
	
	@RequestMapping(value = "/deleteempployee")
	public int deleteEmployee(int id) {
		return repository.deleteEmployee(id);
		//return -1;
	}
	
	
	
}
