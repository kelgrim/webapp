package ekn.learning.webapp.model;

import java.util.UUID;

public class Student {
	private int id;
	private String name;
	private String email;
	private String major;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String asString() {
		return "AsString Method from object, this is the name: " + name;
	}
}
