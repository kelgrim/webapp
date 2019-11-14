package ekn.learning.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class DefaultModel {
	
	private List<String> locations = new ArrayList<>();

	public DefaultModel() {
		locations.add("Some possible URLs to use: ");
		locations.add("localhost:8080/math?type=square&a=12");
		locations.add("localhost:8080/math?type=add&a=12&b=24");
		locations.add("localhost:8080/math?type=divide&a=12&b=24");
		locations.add("localhost:8080/greeting");
		locations.add("localhost:8080/greeting?name=Testname");
		
		locations.add("Showing employees in DB:");
		locations.add("localhost:8080/getemployees");
		locations.add("Searching for specific employee:");
		locations.add("localhost:8080/employee?id=2");
		locations.add("Adding an employee:");
		locations.add("localhost:8080/addemployee?firstname=jane&lastname=doe&email=jane.doe@email.com");
		locations.add("Deleting an employee");
		locations.add("localhost:8080/deleteempployee?id=5901602");
		locations.add("");
		locations.add("");
		
	}
	
	public List getLocations() {
		return locations;
	}
}
