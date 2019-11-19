package ekn.learning.webapp.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ekn.learning.webapp.exceptions.UserDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.UserNotFoundException;
import ekn.learning.webapp.exceptions.UserUpdateFailedException;
import ekn.learning.webapp.exceptions.UserWriteToDbFailedException;
import ekn.learning.webapp.model.User;

@Repository
public class UserJdbcRepository {
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;	
	
	public User findById(int id) throws UserNotFoundException {
		
	    try {
	    	return jdbcTemplate.queryForObject("select * from tbl_users where id=?", 
	    
	    		new Object[] {
	    				id
	        	},
	    		new BeanPropertyRowMapper <User> (User.class));
	    }
	    catch(EmptyResultDataAccessException e){
	    	throw new UserNotFoundException(id);
	    }
	}
	
	public int addUser(User user) {
		String sql = "insert into tbl_users (username, description, email)"
				+ "values (?, ?, ?);";
		
		String username = user.getUsername();
		String description = user.getDescription();
		String email = user.getEmail();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {		
			jdbcTemplate.update(	
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql); //, new String[] {firstName, lastName, email});
				            ps.setString(1, username);
				            ps.setString(2, description);
				            ps.setString(3, email);
				            return ps;
				        }
				    },
				    keyHolder);
		}
		catch(EmptyResultDataAccessException e) {
			throw new UserWriteToDbFailedException();
		}
		catch(DataIntegrityViolationException e) {
			throw new UserWriteToDbFailedException();
		}
		
		return (int) keyHolder.getKey();
	}
	
	//TODO: Change to return userId instead of 1
	public int addUser(String username, String description, String email) {	
		if (username == null || description == null || email == null) {
			throw new UserWriteToDbFailedException();
		}
		
		try{
			//KeyHolder keyHolder = new GeneratedKeyHolder();
			String query = String.format("insert into tbl_users (username, description, email) "
					+ "values ('%s','%s','%s');", username, description, email);
			
			jdbcTemplate.update(query);	
			return 1;
			}
		catch (DataIntegrityViolationException e) {
			throw new UserWriteToDbFailedException();
		}
		
	}
	
	public User getUserAlternative(int id) throws UserNotFoundException{		
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
			String query = "SELECT * FROM TBL_USERS WHERE ID = :id"; 
			User result = npJdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class) );
			return result;
		}
		catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException(id);
			
		}
		
	}
	
	public List<User> getUsers(){
		String sql = "SELECT * FROM tbl_users ORDER BY ID ASC";
		List<User> users = 
				jdbcTemplate.query(sql, 
						   BeanPropertyRowMapper.newInstance(User.class));
		
		return users;
	}
	
	public int deleteUser(int id) throws UserDeleteFromDbFailedException {
		String sql = "DELETE FROM TBL_USERS WHERE ID = ?";
		try {
			int result = jdbcTemplate.update(sql, id);
			if (result == 0) {
				throw new UserDeleteFromDbFailedException(id);
			}
			else return result;
		}
		catch (Exception e) {
			//e.printStackTrace();
			throw new UserDeleteFromDbFailedException(id);
		}
	}
	
	public int updateUser(int id, User user) throws UserUpdateFailedException {
		try {
			String query = String.format(
					"update tbl_users "
					+ "set "
					+ "username = '%s', "
					+ "description = '%s', "
					+ "email = '%s' "
					+ "where id = %s;", user.getUsername(), user.getDescription(), user.getEmail(), id);
			
			int result = jdbcTemplate.update(query);	
			if (result == 0) throw new UserUpdateFailedException(id);
			else	return id;
		}
		catch (Exception e) {
			throw new UserUpdateFailedException(id);
		}
	}
		
}
