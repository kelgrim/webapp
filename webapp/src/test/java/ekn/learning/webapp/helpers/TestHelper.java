package ekn.learning.webapp.helpers;

import java.sql.Timestamp;

import ekn.learning.webapp.model.User;
import ekn.learning.webapp.model.Message;

public class TestHelper {

	public static User getTestUser() {
		User user = new User();
		user.setUsername("Username");
		user.setDescription("User Description");
		user.setEmail("User@Email.com");
		return user;
	}
	
	//Compare two users. This because usually they are not the same objects
	public static boolean compareUser(User a, User b) {
		
		boolean same = true;
		if (!a.getUsername().equals(b.getUsername() ) ) 	 	same = false;
		if (!a.getDescription().equals(b.getDescription() ) ) 	same = false;
		if (!a.getEmail().equals(b.getEmail() ) ) 	 			same = false;
		
		return same;
	}
	
	public static Message getTestMessage() {
		Message msg = new Message();
		msg.setId(1);
		msg.setSenderId(1);
		msg.setRecipientId(3);
		msg.setMessageText("Kelgrim sends message to Barney");
		msg.setMessageRead(false);
		msg.setMessageTimeStamp(new Timestamp(123498712L));
		
		return msg;
		
	}
	
	public static boolean compareMessage(Message a, Message b, boolean testTimeStamp) {
		
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
