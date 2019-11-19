package ekn.learning.webapp.helpers;

import ekn.learning.webapp.model.Employee;
import ekn.learning.webapp.model.Message;

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
	public static boolean compareEmployee(Employee a, Employee b) {
		
		//System.out.println("Comparing" + a.toString() + " with "+ b.toString() );
		boolean same = true;
		if (!a.getFirstName().equals(b.getFirstName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		if (!a.getLastName().equals(b.getLastName() ) ) 	 same = false;
		
		return same;
	}
	
	public static Message getTestMessage() {
		Message msg = new Message();
		msg.setId(1);
		msg.setSenderId(1);
		msg.setRecipientId(3);
		msg.setMessageText("Lokesh sends message to Caption");
		msg.setMessageRead(false);
		
		return msg;
		
	}
	
	public static boolean compareMessage(Message a, Message b, boolean testTimeStamp) {
		
		//System.out.println("--- Comparing the below messages ---");
		//System.out.println(a.toString());
		//System.out.println(b.toString());
		boolean same = true;
		if (a.getId() != b.getId())								same = false;
		if (a.getRecipientId() != b.getRecipientId() )  		same = false;	
		if (a.getSenderId() != b.getSenderId() ) 	 			same = false;		
		if (!a.getMessageText().equals(b.getMessageText() ) ) 	same = false;	
		if (	testTimeStamp && 
				a.getMessageTimeStamp()
				.compareTo(b.getMessageTimeStamp() ) != 0) 		same = false; 
		if (a.isMessageRead() != b.isMessageRead())				same = false;	
		
		System.out.println("The result of the compare function is: " + same);
		return same;
	}
}
