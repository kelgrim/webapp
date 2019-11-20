package ekn.learning.webapp.services;

import static ekn.learning.webapp.helpers.TestHelper.getTestMessage;

import org.hibernate.validator.internal.util.logging.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest
;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import ekn.learning.webapp.exceptions.MessageInvalidArgumentsException;
import ekn.learning.webapp.exceptions.MessageNotFoundException;
import ekn.learning.webapp.exceptions.MessageNotFoundForUserException;
import ekn.learning.webapp.model.Message;
import ekn.learning.webapp.repos.MessageJdbcRepository;

@SpringBootTest
public class MessageServiceTest {
	
	@Mock
	private MessageJdbcRepository repository;
	
	@InjectMocks
	private MessageService service;
	
	private Message testMessageSuccess;
	private Message testMessageFail;
	
	@BeforeEach
    void setMockOutput() {
		testMessageSuccess = getTestMessage();
		testMessageFail = getTestMessage();
		
		List<Message> messageList = new ArrayList<>();
		messageList.add(testMessageSuccess);
		
		Mockito.when(repository.findMessageById(1)).thenReturn(testMessageSuccess);
		Mockito.when(repository.findMessageById(99901)).thenThrow(MessageNotFoundException.class);
		
		Mockito.when(repository.getUserInbox(1)).thenReturn(messageList);
		Mockito.when(repository.getUserInbox(99902)).thenThrow(MessageNotFoundForUserException.class);
		
		Mockito.when(repository.getUserSentMessages(1)).thenReturn(messageList);
		Mockito.when(repository.getUserSentMessages(99903)).thenThrow(MessageNotFoundForUserException.class);
		
		Mockito.when(repository.insertMessage(testMessageSuccess)).thenReturn(1);
		Mockito.when(repository.insertMessage(testMessageFail)).thenThrow(MessageInvalidArgumentsException.class);
		
		Mockito.when(repository.deleteMessage(1)).thenReturn(1);
		Mockito.when(repository.deleteMessage(99904)).thenThrow(MessageNotFoundException.class);
		
		Mockito.when(repository.updateMessage(1, testMessageSuccess)).thenReturn(1);
		Mockito.when(repository.updateMessage(99905, testMessageFail)).thenThrow(MessageNotFoundException.class);
		Mockito.when(repository.updateMessage(99906, testMessageFail)).thenThrow(MessageInvalidArgumentsException.class);
	}
	
	@Test
	public void getMessage_returnMessage() {
		Message result = service.getMessage(1);
		assertEquals(testMessageSuccess, result);
	}
	
	@Test
	public void getMessage_throwException() {
		assertThrows(MessageNotFoundException.class, () -> {service.getMessage(99901);});
	}
	
	@Test
	public void getInbox_returnList() {
		List<Message> result = service.getInbox(1);
		assertEquals(testMessageSuccess, result.get(0));
	}
	
	@Test
	public void getInbox_throwError() {
		assertThrows(MessageNotFoundForUserException.class, () -> {service.getInbox(99902);});
	}
	
	@Test
	public void getSentItems_returnList() {
		List<Message> result = service.getSentItems(1);
		assertEquals(testMessageSuccess, result.get(0));
	}
	
	@Test
	public void getSentItems_throwError() {
		assertThrows(MessageNotFoundForUserException.class, () -> {service.getSentItems(99903);});
	}
	
	@Test
	public void sendMessage_returnMessage() {
		Message result = service.sendMessage(testMessageSuccess);
		assertEquals(testMessageSuccess, result);
	}
	
	@Test
	public void sendMessage_throwException() {
		assertThrows(MessageInvalidArgumentsException.class, () -> {service.sendMessage(testMessageFail);});
	}
	
	@Test
	public void deleteMessage_returnInt() {
		int result = service.deleteMessage(1);
		assertEquals(1, result);
	}
	
	@Test
	public void deleteMessage_throwException() {
		assertThrows(MessageNotFoundException.class, () -> {service.deleteMessage(99904);});
	}
	
	@Test
	public void updateMessage_returnMessage() {
		Message actualMessage = service.updateMessage(1, testMessageSuccess);
		assertEquals(testMessageSuccess, actualMessage);
	}
	
	@Test
	public void updateMessage_throwMessageNotFound() {
		assertThrows(MessageNotFoundException.class, () -> {service.updateMessage(99905, testMessageFail);});
	}
	
	@Test
	public void updateMessage_throwMessageInvalidArgumentsException() {
		assertThrows(MessageInvalidArgumentsException.class, () -> {service.updateMessage(99906,  testMessageFail);});
	}
	
}
