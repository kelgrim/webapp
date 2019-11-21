package ekn.learning.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.User;
import ekn.learning.webapp.services.ServiceResponseMessage;
import ekn.learning.webapp.services.UserService;

@RestController
@RequestMapping(value = "/user") 
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers(){
		return service.getUsers();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable() Integer id) {
			  return service.getUser(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User addUser(@RequestBody() User user) {
		return service.addUser(user);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable() Integer id, @RequestBody() User user) {
		return service.updateUser(id, user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ServiceResponseMessage deleteUser(@PathVariable() Integer id) {
		return service.deleteUser(id);
	}
	
}
