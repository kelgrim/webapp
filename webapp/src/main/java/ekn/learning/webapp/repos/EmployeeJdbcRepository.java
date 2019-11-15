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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ekn.learning.webapp.exceptions.EmployeeDeleteFromDbFailedException;
import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
import ekn.learning.webapp.exceptions.EmployeeWriteToDbFailedException;
//import ekn.learning.webapp.model.Department;
import ekn.learning.webapp.model.Employee;

@Repository
public class EmployeeJdbcRepository {
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;	
	
	public Employee findById(int id) throws EmployeeNotFoundException {
		
	    try {
	    	return jdbcTemplate.queryForObject("select * from tbl_employees where id=?", 
	    
	    		new Object[] {
	    				id
	        	},
	    		new BeanPropertyRowMapper <Employee> (Employee.class));
	    }
	    catch(EmptyResultDataAccessException e){
	    	throw new EmployeeNotFoundException(id);
	    }
	}
	
	public int addEmployeeTwo(Employee employee) {
		String sql = "insert into tbl_employees (FIRST_NAME, LAST_NAME, EMAIL)"
				+ "values (?, ?, ?);";
		
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String email = employee.getEmail();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {		
			jdbcTemplate.update(	
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps =
				                connection.prepareStatement(sql, new String[] {firstName, lastName, email});
				            ps.setString(1, firstName);
				            ps.setString(2, lastName);
				            ps.setString(3, email);
				            return ps;
				        }
				    },
				    keyHolder);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EmployeeWriteToDbFailedException();
		}
		//TODO: change cast to int to something better. 
		return (int) keyHolder.getKey();
	}
	
	
	public int addEmployee(Employee employee) throws EmployeeWriteToDbFailedException{	
		
		try {			
			String query = "insert into tbl_employees (FIRST_NAME, LAST_NAME, EMAIL)"
					+ "values (:firstName, :lastName, :email);";
			
			npJdbcTemplate.update(query, new BeanPropertySqlParameterSource(employee));
			return 1;
		}
		catch (DataIntegrityViolationException e) {
			throw new EmployeeWriteToDbFailedException();
		}
	}
	
	public int addEmployee(String firstName, String lastName, String email) {	
		if (firstName == null || lastName == null || email == null) {
			throw new EmployeeWriteToDbFailedException();
		}
		
		
		try{
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String query = String.format("insert into tbl_employees (FIRST_NAME, LAST_NAME, EMAIL) "
					+ "values ('%s','%s','%s');", firstName, lastName, email);
			
			jdbcTemplate.update(query);	
			return 1;
			}
		catch (DataIntegrityViolationException e) {
			throw new EmployeeWriteToDbFailedException();
		}
		
	}
	
	public Employee getEmployeeAlternative(int id) throws EmployeeNotFoundException{		
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
			String query = "SELECT * FROM TBL_EMPLOYEES WHERE ID = :id"; 
			Employee result = npJdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Employee>(Employee.class) );
			return result;
		}
		catch(EmptyResultDataAccessException e){
			throw new EmployeeNotFoundException(id);
			
		}
	

		
	}
	
	public List<Employee> getEmployees(){
		String sql = "SELECT * FROM tbl_employees ORDER BY ID ASC";
		List<Employee> employees = 
				jdbcTemplate.query(sql, 
						   BeanPropertyRowMapper.newInstance(Employee.class));
		
		return employees;
	}
	
	public int deleteEmployee(int id) throws EmployeeDeleteFromDbFailedException {
		String sql = "DELETE FROM TBL_EMPLOYEES WHERE ID = ?";
		try {
			int result = jdbcTemplate.update(sql, id);
			System.out.println(result + " <----------------- Result here");
			if (result == 0) {
				throw new EmployeeDeleteFromDbFailedException(id);
			}
			else return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeDeleteFromDbFailedException(id);
		}
		
		
	}
}
