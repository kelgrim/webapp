package ekn.learning.webapp.model;


public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
//	private Department[] departments;
	
	public Employee() {
		super();
		/*
		departments = new Department[2];
		departments[0]= new Department("dep name", "dep location");
		departments[1]= new Department("dep name 2", "dep location 2");
		*/
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/*public Department[] getDepartments() {
		return departments;
	}
	
	public void setDepartments(Department[] departments) {
		this.departments = departments;
	}	
	*/
}
