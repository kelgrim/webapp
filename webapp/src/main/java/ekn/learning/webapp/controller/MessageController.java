package ekn.learning.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.Message;
import ekn.learning.webapp.services.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	MessageService service;
	
	@RequestMapping(path="/message/{messageId}", method=RequestMethod.GET)
	public Message getMessage(@PathVariable() Integer messageId ) {
		return service.getMessage(messageId);
	}
	
	@RequestMapping(path="/message", method=RequestMethod.POST)
	public Message sendMessage(@RequestBody() Message message ) {
		return service.sendMessage(message);
	}
	
	@RequestMapping(path="/message/{messageId}", method=RequestMethod.DELETE )
	public int deleteMessage(@PathVariable() Integer messageId)	{
		return service.deleteMessage(messageId);
	}
	
	@RequestMapping(path="/message/{messageId}", method=RequestMethod.PATCH)
	public Message updateMessage(@PathVariable() Integer messageId, @RequestBody() Message message) {
		return service.updateMessage(messageId, message);
	}
	
	
	@RequestMapping(path="/user/{userId}/inbox", method=RequestMethod.GET)
	public List<Message> getInbox(@PathVariable() Integer userId){
		return service.getInbox(userId);
	}
	
	@RequestMapping(path="/user/{userId}/sent", method=RequestMethod.GET)
	public List<Message> getSentItems(@PathVariable() Integer userId){
		return service.getSentItems(userId);
	}
	

	
	
}
