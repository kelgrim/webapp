package ekn.learning.webapp.services;

import static ekn.learning.webapp.helpers.TestHelper.getTestEmployee;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import ekn.learning.webapp.model.Employee;

//import org.mockito.MockitoAnnotations.Mock;

import ekn.learning.webapp.repos.EmployeeJdbcRepository;

//@TestInstance(value = LifeCycle.PER_METHOD)
public class EmployeeServiceTest {
	
	//@Autowired
	//private EmployeeService service;
	
	@Mock
	EmployeeJdbcRepository mockRepository;
	
//	 @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	//@InjectMocks
	//private EmployeeService service;
	
	//@BeforeAll
	//public void setUp() throws Exception{
	//	MockitoAnnotations.initMocks(this);		
	//}
	
	@Test
	public void myTest()
	{
		//Create mock
		//EmployeeJdbcRepository mockRepository = mock(EmployeeJdbcRepository.class);
		when(mockRepository.findById(22) ).thenReturn(getTestEmployee());
		Employee emp = mockRepository.findById(22);
		//Employee emp = service.getEmployee(999);
		System.out.println(emp.toString());
	}
	
	
	/*
	@Mock
	private EmployeeJdbcRepository mockRepository;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void testSomething() {
		
	}

	@Mock
	private EmployeeJdbcRepository mockRepository;
	
	@InjectMocks
	private EmployeeService service;
	
	@BeforeAll
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void doTest() {
		//when(mockRepository.findById(any(Integer.class) ) ).thenReturn(new Employee());
		when(mockRepository.findById(1).thenReturn(new Employee());
	}
	*/
	
}
