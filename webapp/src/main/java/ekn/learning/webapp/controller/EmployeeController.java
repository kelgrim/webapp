package ekn.learning.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.Employee;
import ekn.learning.webapp.model.Message;
import ekn.learning.webapp.repos.TimestampTestClass;
import ekn.learning.webapp.services.EmployeeService;

@RestController
@RequestMapping(value = "/employee") 
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	//TODO: Remove the messageRep, only added for quick test
	@Autowired
	private TimestampTestClass messageRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> getEmployees(){
		return service.getEmployees();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable() Integer id) {
			  return service.getEmployee(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Employee addEmployee(@RequestBody() Employee employee) {
		return service.addEmployee(employee);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public Employee updateEmployee(@PathVariable() Integer id, @RequestBody() Employee employee) {
		return service.updateEmployee(id, employee);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public int deleteEmployee(@PathVariable() Integer id) {
		return service.deleteEmployee(id);
	}
	
	//TODO: Remove this mapping
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Message testTimeStamp() {
		//messageRepo.getTimeStamp(1);
		Message message = messageRepo.getMessage(1);
		return message;
	}

}
