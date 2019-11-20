package ekn.learning.webapp.repos;

import static ekn.learning.webapp.helpers.TestHelper.compareUser;
import static ekn.learning.webapp.helpers.TestHelper.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.UserDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.UserInvalidArgumentsException;
import ekn.learning.webapp.exceptions.UserNotFoundException;
import ekn.learning.webapp.exceptions.UserUpdateFailedException;
import ekn.learning.webapp.exceptions.UserWriteToDbFailedException;
import ekn.learning.webapp.model.User;

@SpringBootTest
public class UserJdbcRepositoryTest {
	
	/*
	 * Note that these tests use the embedded database for the results. 
	 */
	
	@Autowired
	private UserJdbcRepository repository;
	
	
	@Test
	public void findById_returnUser() {
		User user = repository.findById(1);
		
		assertEquals(user.getId() , new Integer(1));
		assertEquals(user.getUsername(), "Kelgrim");
		assertEquals(user.getDescription(), "Developer of this Rest service");
		assertEquals(user.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void findById_throwException() {
		assertThrows(UserNotFoundException.class, () -> {repository.findById(99999);} );
	}
	
	@Test
	public void addUser_returnUser() {
		User user = getTestUser();
		int result = repository.addUser(user);
		assertTrue(result > 0);
	}
	
	@Test
	public void addUser_throwException() {
		User user = new User();
		assertThrows(UserWriteToDbFailedException.class, () -> {repository.addUser(user);} ); 
	}
		
	
	@Test
	public void addUserWithParams_returnInt() {
		int result = repository.addUser("John", "Williams", "email@somewhere.nl");
		assertTrue(result == 1);
	}
	
	@Test
	public void addUserWithParams_throwException() {
		assertThrows(UserWriteToDbFailedException.class, () -> {repository.addUser(null,"","");});
	}
	
	@Test
	public void getUserAlternative_returnUser() {
		User user = repository.getUserAlternative(1);
		assertEquals(user.getId() , new Integer(1));
		assertEquals(user.getUsername(), "Kelgrim");
		assertEquals(user.getDescription(), "Developer of this Rest service");
		assertEquals(user.getEmail(), "abc@gmail.com");	
	}
	
	@Test
	public void getUserAlternative_throwException() {
		assertThrows(UserNotFoundException.class, () -> {repository.getUserAlternative(9999999);} );
	}
	
	//TODO: Find better (and more reliable) way to to compare the values in the list with
	// data that is known to be in DB. 
	@Test
	public void getUser_returnList() {
		List<User> list = repository.getUsers();
		
		assertTrue(compareUser(repository.findById(1), list.get(0) ) );
		assertTrue(compareUser(repository.findById(2), list.get(1) ) );
		assertTrue(compareUser(repository.findById(3), list.get(2) ) );
	}
	
	@Test
	public void deleteUser_returnInt() {
		int id = repository.addUser(getTestUser() );
		int result = repository.deleteUser(id);
		assertTrue(result > 0);
	}
	
	@Test
	public void deleteUser_throwException() {
		assertThrows(UserDeleteFromDbFailedException.class, () -> {repository.deleteUser(9999999);});
	}
	
	@Test
	public void updateUser_returnInt() {
		User testUser = getTestUser();
		testUser.setUsername("First");
		testUser.setDescription("Last");
		testUser.setEmail("mail");
		int userId = repository.addUser(testUser);
		int updatedId = repository.updateUser(userId, getTestUser());
		assertEquals(userId, updatedId);
	}
	
	@Test
	public void updateUser_throwException() {
		
		User user = getTestUser();
		assertThrows(UserUpdateFailedException.class, () -> {repository.updateUser(99999, user);} );
	}
	
	@Test
	public void updateUser_throwInvalidArgumentsException() {
		
		User user = new User();
		assertThrows(UserInvalidArgumentsException.class, () -> {repository.updateUser(1, user);} );
	}
			
}
