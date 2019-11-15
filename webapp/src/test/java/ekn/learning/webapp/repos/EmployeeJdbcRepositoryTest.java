package ekn.learning.webapp.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.exceptions.EmployeeWriteToDbFailedException;
import ekn.learning.webapp.model.Employee;

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
	
	//TODO: Employee getEmployeeAlternative(int id){		
	//TODO: List<Employee> getEmployees(){
	//TODO: public int deleteEmployee(int id) {
	
	private Employee getTestEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("test");
		employee.setLastName("last test");
		employee.setEmail("email");
		return employee;
	}
}
