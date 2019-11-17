package ekn.learning.webapp.helpers;

import ekn.learning.webapp.model.Employee;

public class TestHelper {
	//Just get a random employee quickly
	public static Employee getTestEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("Employee First Name");
		employee.setLastName("Employee Last Name");
		employee.setEmail("Employee Email");
		return employee;
	}
	
	//Compare two employees. This because usually they are not the same objects
	public static boolean compare(Employee a, Employee b) {
		
		System.out.println("Comparing" + a.toString() + " with "+ b.toString() );
		boolean same = true;
		if (!a.getFirstName().equals(b.getFirstName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		
		return same;
	}
}
