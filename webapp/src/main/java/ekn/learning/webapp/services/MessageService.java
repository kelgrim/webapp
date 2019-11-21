package ekn.learning.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ekn.learning.webapp.model.Message;
import ekn.learning.webapp.repos.MessageJdbcRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageJdbcRepository repository;
	
	public Message getMessage(int messageId) {
		return repository.findMessageById(messageId);
	}
	
	public List<Message> getInbox(int userId){
		return repository.getUserInbox(userId);
	}
	
	public List<Message> getSentItems(int userId){
		return repository.getUserSentMessages(userId);
	}
	
	public Message sendMessage(Message message) {
		int messageId = repository.insertMessage(message);
		return repository.findMessageById(messageId);
	}
	
	public ServiceResponseMessage deleteMessage(int messageId) {
		repository.deleteMessage(messageId);
		return new ServiceResponseMessage("Delete Message","Messager with id: "+ messageId + " was succesfully deleted" );
	}

	public Message updateMessage(int messageId, Message updateMessage) {
		repository.updateMessage(messageId, updateMessage);
		return repository.findMessageById(messageId);
	}
}
