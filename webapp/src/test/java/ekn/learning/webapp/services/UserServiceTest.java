package ekn.learning.webapp.services;

import static ekn.learning.webapp.helpers.TestHelper.compareUser;
import static ekn.learning.webapp.helpers.TestHelper.getTestUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ekn.learning.webapp.exceptions.UserDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.UserNotFoundException;
import ekn.learning.webapp.exceptions.UserUpdateFailedException;
import ekn.learning.webapp.exceptions.UserWriteToDbFailedException;
import ekn.learning.webapp.model.User;
import ekn.learning.webapp.repos.UserJdbcRepository;

@SpringBootTest
public class UserServiceTest {
	
	   @Mock
	    private UserJdbcRepository repository;
	    
	    @InjectMocks
	    private UserService service;
	    
	    private User testUserSuccess;
	    private User testUserException;
	   
	    @BeforeEach
	    void setMockOutput() {
	    	
	    	testUserSuccess = getTestUser();
	    	testUserException = getTestUser();
	    	
	    	Mockito.when(repository.findById(1)).thenReturn(testUserSuccess);
	    	Mockito.when(repository.findById(99901)).thenThrow(UserNotFoundException.class);
	    	
	    	List<User> users= new ArrayList<>();
	    	users.add(getTestUser());
	    	Mockito.when(repository.getUsers()).thenReturn(users);
	    	
	    	
	    	Mockito.when(repository.addUser(testUserSuccess) ).thenReturn(1);
	    	Mockito.when(repository.addUser(testUserException) ).thenThrow(UserWriteToDbFailedException.class);
	    	
	    	Mockito.when(repository.deleteUser(1)).thenReturn(1);
	    	Mockito.when(repository.deleteUser(99901)).thenThrow(UserDeleteFromDbFailedException.class);
	    	
	    	
	    	Mockito.when(repository.updateUser(1, testUserSuccess )).thenReturn(1);
	    	Mockito.when(repository.updateUser(99901, testUserException )).thenThrow(UserUpdateFailedException.class);
	    }
	    
	    @Test
	    public void getUser_returnUser() {
	    	User user = service.getUser(1); //repository.findById(999); //
	    	assertTrue(compareUser(getTestUser(), user) );
	    }
	    
	    @Test
	    public void getUser_returnException() {
	    	assertThrows(UserNotFoundException.class, () -> {service.getUser(99901);});
	    }
	    
	    @Test
	    public void getUsers_returnUsers() {
	    	List<User> l = service.getUsers();
	    	assertTrue(compareUser(getTestUser(),l.get(0)) );
	    }
	    
	    @Test
	    public void addUser_returnUser() {
	    	User result = service.addUser(testUserSuccess);
	    	assertEquals(testUserSuccess,result);
	    }
	    
	    @Test
	    public void addUser_throwException() {
	    	assertThrows(UserWriteToDbFailedException.class, () -> {service.addUser(testUserException );});
	    }
	    
	    @Test
	    public void deleteUser_returnId() {
	    	//assertEquals(1, service.deleteUser(1));
	    	ServiceResponseMessage responseMsg = service.deleteUser(1);
	    	assertEquals("Delete User", responseMsg.getOperation());
	    	assertEquals("Deletion of user with id: 1 was succesfull",responseMsg.getStatus());
	    }
	    
	    @Test
	    public void deleteUser_throwException() {
	    	assertThrows(UserDeleteFromDbFailedException.class, () -> {service.deleteUser(99901);});
	    }
	    
	    @Test
	    public void updateUser_returnUser() {
	    	User result = service.updateUser(1, testUserSuccess);
	    	assertEquals(testUserSuccess, result);
	    }
	    
	    @Test
	    public void updateUser_throwException() {
	    	assertThrows(UserUpdateFailedException.class, 
	    			() -> {service.updateUser(99901, testUserException);});
	    }
}
