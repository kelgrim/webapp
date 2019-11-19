package ekn.learning.webapp.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static ekn.learning.webapp.helpers.TestHelper.compareEmployee;
import static ekn.learning.webapp.helpers.TestHelper.getTestEmployee;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.EmployeeDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.exceptions.EmployeeUpdateFailedException;
import ekn.learning.webapp.exceptions.EmployeeWriteToDbFailedException;
import ekn.learning.webapp.model.Employee;

@SpringBootTest
public class EmployeeJdbcRepositoryTest {
	
	/*
	 * Note that these tests use the embedded database for the results. 
	 */
	
	@Autowired
	private EmployeeJdbcRepository repository;
	
	
	@Test
	public void findById_returnEmployee() {
		Employee employee = repository.findById(1);
		
		assertEquals(employee.getId() , new Integer(1));
		assertEquals(employee.getFirstName(), "Lokesh Local");
		assertEquals(employee.getLastName(), "Gupta");
		assertEquals(employee.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void findById_throwException() {
		assertThrows(EmployeeNotFoundException.class, () -> {repository.findById(99999);} );
	}
	
	@Test
	public void addEmployee_returnEmployee() {
		Employee employee = getTestEmployee();
		int result = repository.addEmployee(employee);
		assertTrue(result > 0);
	}
	
	@Test
	public void addEmployee_throwException() {
		Employee employee = new Employee();
		assertThrows(EmployeeWriteToDbFailedException.class, () -> {repository.addEmployee(employee);} ); 
	}
		
	
	@Test
	public void addEmployeeWithParams_returnEmployee() {
		int result = repository.addEmployee("John", "Williams", "email@somewhere.nl");
		assertTrue(result == 1);
	}
	
	@Test
	public void addEmployeeWithParams_throwException() {
		assertThrows(EmployeeWriteToDbFailedException.class, () -> {repository.addEmployee(null,"","");});
	}
	
	@Test
	public void getEmployeeAlternative_returnEmployee() {
		Employee employee = repository.getEmployeeAlternative(1);
		assertEquals(employee.getId() , new Integer(1));
		assertEquals(employee.getFirstName(), "Lokesh Local");
		assertEquals(employee.getLastName(), "Gupta");
		assertEquals(employee.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void getEmployeeAlternative_throwException() {
		assertThrows(EmployeeNotFoundException.class, () -> {repository.getEmployeeAlternative(9999999);} );
	}
	
	//TODO: Find better (and more reliable) way to to compare the values in the list with
	// data that is known to be in DB. 
	@Test
	public void getEmployees_returnList() {
		List<Employee> list = repository.getEmployees();
		
		assertTrue(compareEmployee(repository.findById(1), list.get(0) ) );
		assertTrue(compareEmployee(repository.findById(2), list.get(1) ) );
		assertTrue(compareEmployee(repository.findById(3), list.get(2) ) );
	}
	
	@Test
	public void deleteEmployee_returnInt() {
		int id = repository.addEmployee(getTestEmployee() );
		int result = repository.deleteEmployee(id);
		assertTrue(result > 0);
	}
	
	@Test
	public void deleteEmployee_throwException() {
		assertThrows(EmployeeDeleteFromDbFailedException.class, () -> {repository.deleteEmployee(9999999);});
	}
	
	@Test
	public void updateEmployee_returnInt() {
		Employee testEmployee = getTestEmployee();
		testEmployee.setFirstName("First");
		testEmployee.setLastName("Last");
		testEmployee.setEmail("mail");
		int employeeId = repository.addEmployee(testEmployee);
		int updatedId = repository.updateEmployee(employeeId, getTestEmployee());
		assertEquals(employeeId, updatedId);
	}
	
	@Test
	public void updateEmployee_throwException() {
		
		Employee employee = getTestEmployee();
		assertThrows(EmployeeUpdateFailedException.class, () -> {repository.updateEmployee(99999, employee);} );
	}
			
}
