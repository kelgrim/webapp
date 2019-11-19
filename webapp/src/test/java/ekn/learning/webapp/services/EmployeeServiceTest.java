package ekn.learning.webapp.services;

import static ekn.learning.webapp.helpers.TestHelper.compareEmployee;
import static ekn.learning.webapp.helpers.TestHelper.getTestEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.EmployeeDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.exceptions.EmployeeUpdateFailedException;
import ekn.learning.webapp.exceptions.EmployeeWriteToDbFailedException;
import ekn.learning.webapp.model.Employee;
import ekn.learning.webapp.repos.EmployeeJdbcRepository;

@SpringBootTest
public class EmployeeServiceTest {
	
	   @Mock
	    private EmployeeJdbcRepository repository;
	    
	    @InjectMocks
	    private EmployeeService service;
	    
	    private Employee testEmployeeSuccess;
	    private Employee testEmployeeException;
	   
	    @BeforeEach
	    void setMockOutput() {
	    	
	    	testEmployeeSuccess = getTestEmployee();
	    	testEmployeeException = getTestEmployee();
	    	
	    	Mockito.when(repository.findById(1)).thenReturn(testEmployeeSuccess);
	    	Mockito.when(repository.findById(99901)).thenThrow(EmployeeNotFoundException.class);
	    	
	    	List<Employee> emps= new ArrayList<>();
	    	emps.add(getTestEmployee());
	    	Mockito.when(repository.getEmployees()).thenReturn(emps);
	    	
	    	
	    	Mockito.when(repository.addEmployee(testEmployeeSuccess) ).thenReturn(1);
	    	Mockito.when(repository.addEmployee(testEmployeeException) ).thenThrow(EmployeeWriteToDbFailedException.class);
	    	
	    	Mockito.when(repository.deleteEmployee(1)).thenReturn(1);
	    	Mockito.when(repository.deleteEmployee(99901)).thenThrow(EmployeeDeleteFromDbFailedException.class);
	    	
	    	
	    	Mockito.when(repository.updateEmployee(1, testEmployeeSuccess )).thenReturn(1);
	    	Mockito.when(repository.updateEmployee(99901, testEmployeeException )).thenThrow(EmployeeUpdateFailedException.class);
	    }
	    
	    @Test
	    public void getEmployee_returnEmployee() {
	    	Employee employee = service.getEmployee(1); //repository.findById(999); //
	    	assertTrue(compareEmployee(getTestEmployee(), employee) );
	    }
	    
	    @Test
	    public void getEmployee_returnException() {
	    	assertThrows(EmployeeNotFoundException.class, () -> {service.getEmployee(99901);});
	    }
	    
	    @Test
	    public void getEmployees_returnEmployees() {
	    	List<Employee> l = service.getEmployees();
	    	assertTrue(compareEmployee(getTestEmployee(),l.get(0)) );
	    }
	    
	    @Test
	    public void addEmployee_returnCustomer() {
	    	Employee result = service.addEmployee(testEmployeeSuccess);
	    	assertEquals(testEmployeeSuccess,result);
	    }
	    
	    @Test
	    public void addEmployee_throwException() {
	    	assertThrows(EmployeeWriteToDbFailedException.class, () -> {service.addEmployee(testEmployeeException );});
	    }
	    
	    @Test
	    public void deleteEmployee_returnId() {
	    	assertEquals(1, service.deleteEmployee(1));
	    }
	    
	    @Test
	    public void deleteEmployee_throwException() {
	    	assertThrows(EmployeeDeleteFromDbFailedException.class, () -> {service.deleteEmployee(99901);});
	    }
	    
	    @Test
	    public void updateEmployee_returnCustomer() {
	    	Employee result = service.updateEmployee(1, testEmployeeSuccess);
	    	assertEquals(testEmployeeSuccess, result);
	    }
	    
	    @Test
	    public void updateEmployee_throwException() {
	    	assertThrows(EmployeeUpdateFailedException.class, 
	    			() -> {service.updateEmployee(99901, testEmployeeException);});
	    }
}
