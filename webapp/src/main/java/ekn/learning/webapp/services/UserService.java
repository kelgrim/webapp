package ekn.learning.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ekn.learning.webapp.model.User;
import ekn.learning.webapp.repos.UserJdbcRepository;


@Service
public class UserService {

	@Autowired
	private UserJdbcRepository repository;
	
	public User getUser(int id) {
		return  repository.findById(id);
	}
	
	public List<User> getUsers(){
		return repository.getUsers();
	}
	
	public User addUser(User user) {
		int userId = repository.addUser(user);	
		return repository.findById(userId);	
	}

	public Integer deleteUser(int id) {
		return repository.deleteUser(id);
	}

	public User updateUser(int id, User user) {
		int updatedId =  repository.updateUser(id, user);
		return repository.findById(updatedId);
	}	
	
}
