package ekn.learning.webapp.model;

public class Department {
	private String name;
	private String description;
	
	public Department() {
		super();
	}
	
	public Department(String name, String location) {
		this.name = name;
		this.description = location;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getdescription() {
		return description;
	}
	
	
	public void setdescription(String location) {
		this.description = location;
	}
	
	
}
