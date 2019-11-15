package ekn.learning.webapp.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import java.util.ArrayList;
import java.util.List;

//import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.EmployeeDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.exceptions.EmployeeWriteToDbFailedException;
import ekn.learning.webapp.model.Employee;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsInAnyOrder;
//import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
public class EmployeeJdbcRepositoryTest {
	
	@Autowired
	private EmployeeJdbcRepository repository;

	/*
	 * findById(id) tests
	 */
	
	@Test
	public void testFindByIdSucces() {
		Employee employee = repository.findById(1);
		
		assertEquals(employee.getId() , new Integer(1));
		assertEquals(employee.getFirstName(), "Lokesh Local");
		assertEquals(employee.getLastName(), "Gupta");
		assertEquals(employee.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void testFindByIdUnknownId() {
		assertThrows(EmployeeNotFoundException.class, () -> {repository.findById(99999);} );
	}
		
	/*
	 * addEmployee tests
	 */
	
	
	@Test
	public void addEmployeeSuccess() {
		Employee employee = getTestEmployee();
		int result = repository.addEmployee(employee);
		assertTrue(result == 1);
	}
	
	@Test
	public void addEmployeeFailure() {
		Employee employee = new Employee();
		assertThrows(EmployeeWriteToDbFailedException.class, () -> {repository.addEmployee(employee);} ); 
	}
		
	/*
	 * addEmployee(String firstName, String lastName, String email) testcases
	 */
	
	@Test
	public void addEmployeeWithParamsSuccess() {
		int result = repository.addEmployee("John", "Williams", "email@somewhere.nl");
		assertTrue(result == 1);
	}
	
	@Test
	public void addEmployeeWithFailure() {
		assertThrows(EmployeeWriteToDbFailedException.class, () -> {repository.addEmployee(null,"","");});
	}
	
	/* 
	 * getEmployeeAlternative(int id) testcases; 
	 */
	
	@Test
	public void getEmployeeAlternativeSuccess() {
		Employee employee = repository.getEmployeeAlternative(1);
		assertEquals(employee.getId() , new Integer(1));
		assertEquals(employee.getFirstName(), "Lokesh Local");
		assertEquals(employee.getLastName(), "Gupta");
		assertEquals(employee.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void getEmployeeAlternativeFailure() {
		assertThrows(EmployeeNotFoundException.class, () -> {repository.getEmployeeAlternative(9999999);} );
	}
	
	//TODO: List<Employee> getEmployees(){
	//TODO: Find better (and more reliable) way to to compare the values in the list with
	// data that is known to be in DB. 
	@Test
	public void getEmployeesSuccess() {
		List<Employee> list = repository.getEmployees();
		
		assertTrue(compare(repository.findById(1), list.get(0) ) );
		assertTrue(compare(repository.findById(2), list.get(1) ) );
		assertTrue(compare(repository.findById(3), list.get(2) ) );
	}
	
	/*
	 * Testcases for deleteEmployee(id)
	 */
	
	@Test
	public void deleteEmployeeSuccess() {
		int id = repository.addEmployeeTwo(getTestEmployee() );
		int result = repository.deleteEmployee(id);
		assertTrue(result == 1);
	}
	
	@Test
	public void deleteEmployeeFailed() {
		//int id = repository.addEmployeeTwo(getTestEmployee() );
		assertThrows(EmployeeDeleteFromDbFailedException.class, () -> {repository.deleteEmployee(9999999);});
		//assertTrue(result == -1);
	}
	
	
	
	
	/* Helper functions
	 * 
	 */
	
	//Just get a random employee quickly
	private Employee getTestEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("last test");
		employee.setEmail("email");
		return employee;
	}
	
	private boolean compare(Employee a, Employee b) {
		
		System.out.println("Comparing" + a.toString() + " with "+ b.toString() );
		boolean same = true;
		if (!a.getFirstName().equals(b.getFirstName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		
		return same;
	}
	
	
	
}
