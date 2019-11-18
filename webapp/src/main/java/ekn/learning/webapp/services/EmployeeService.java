package ekn.learning.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	public Employee addEmployee(Employee employee) {
		int employeeId = repository.addEmployee(employee);	
		return repository.findById(employeeId);	
	}

	public Integer deleteEmployee(int id) {
		return repository.deleteEmployee(id);
	}

	public Employee updateEmployee(int id, Employee employee) {
		int updatedId =  repository.updateEmployee(id, employee);
		return repository.findById(updatedId);
	}

	//TODO: Delete this method. Added with IDEA to test making changes with different workstationss
	public int testMethod(){
		return 1;
	}
	

	
	
	
}
