package ekn.learning.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class CalculatorModel {
	private String name;
	private String operation;
	private int result;
	
	public CalculatorModel(String name) {
		this.name = name;
	}

	//private List<String> result = new ArrayList<>();
	
	public void doCalc(String type, int a) {
		switch(type) {
		case "square":
			doSquare(a);
			break;
		}
	}
	
	public void doCalc(String type, int a, int b) {
		switch(type) {
		case "add": 
			doAddition(a,b);
			break;
		
		case "divide":
			doDivision(a,b);
			break;
		}	
		
	}
	
	
	private void doAddition(int a, int b) {
		operation = a + " + " + b;
		//result.add(a + " + " + b);
		result = a + b;
		//result.add("" + r);
	}
	
	private void doSquare(int a) {
		operation = a + " * " + a;
		result = a*a;
		//result.add(""+r);
	}
	
	private void doDivision(int a, int b) {
		operation = a + " / " + b + "(integer result for convenience)";
		result = a / b;
		//result.add(""+r);
	}
	
	public String getName() {
		return name;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public int getResult() {
		return result;
	}
	
}
