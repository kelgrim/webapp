package ekn.learning.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.model.Employee;
import ekn.learning.webapp.repos.EmployeeJdbcRepository;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeJdbcRepository repository;
	
	//TODO: Return the employee instead of the Id
	public Employee getEmployee(int id) {
		return  repository.findById(id);
	}
	
	public List<Employee> getEmployees(){
		return repository.getEmployees();
	}
	
	public int addEmployee(Employee employee) {
		return repository.addEmployee(employee);	
	}

	public Integer deleteEmployee(int id) {
		return repository.deleteEmployee(id);
	}
	
	
	
	
	
}
