package ekn.learning.webapp.repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ekn.learning.webapp.exceptions.EmployeeNotFoundException;
//import ekn.learning.webapp.model.Department;
import ekn.learning.webapp.model.Employee;

@Repository
public class EmployeeJdbcRepository {
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;
	
	//@Autowired 
	// PlatformTransactionManager platformTransactionManager;
	
	
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
	   
		/*
		Employee employee = new Employee();
		employee.setId(id);
		employee.setEmail("email");
		employee.setFirstName("first name");
		employee.setLastName("last name");
		employee.setDepartment(new Department("dep name", "dep loc"));
		
		return employee;
		*/
	}
	
	
	public int addEmployee(Employee employee) {	
		
		try {			
			String query = "insert into tbl_employees (FIRST_NAME, LAST_NAME, EMAIL)"
					+ "values (:firstName, :lastName, :email);";
			
			//System.out.println
			
			npJdbcTemplate.update(query, new BeanPropertySqlParameterSource(employee));
			return 1;
		}
		catch (Exception e) {
			System.out.println("Adding employee to db failed");
			return -1;
		}
	}
	
	
	
	//Old versions
	/*
	public int addEmployee(Employee employee) {	
		
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource();
			((MapSqlParameterSource) namedParameters).addValue("firstName", employee.getFirstName() );
			((MapSqlParameterSource) namedParameters).addValue("lastName", employee.getLastName() );
			((MapSqlParameterSource) namedParameters).addValue("email", employee.getEmail() );
			
			String query = "insert into tbl_employees (FIRST_NAME, LAST_NAME, EMAIL)"
					+ "values (:firstName, :lastName, :email);";
			
			npJdbcTemplate.update(query, namedParameters);
			return 1;
		}
		catch (Exception e) {
			System.out.println("Adding employee to db failed");
			return -1;
		}
	}
	*/
	
	public int addEmployee(String firstName, String lastName, String email) {	
		try{
			int id = (int)(Math.random() * 9999999) + 1;
			String query = String.format("insert into tbl_employees (ID, FIRST_NAME, LAST_NAME, EMAIL) "
					+ "values (%s,'%s','%s','%s');", id, firstName, lastName, email);
			
			jdbcTemplate.update(query);	
			return id;
			
			}
		catch (Exception e) {
			 // platformTransactionManager.rollback(status);
			  System.out.println("Failed");
		}
		
		return -1;
		
	}
	
	public Employee getEmployeeAlternative(int id){		

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
	 	String query = "SELECT * FROM TBL_EMPLOYEES WHERE ID = :id"; 
		Employee result = npJdbcTemplate.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Employee>(Employee.class) );
		return result;
	

		
	}
	
	public List<Employee> getEmployees(){
		String sql = "SELECT * FROM tbl_employees";
		List<Employee> employees = 
				jdbcTemplate.query(sql, 
						   BeanPropertyRowMapper.newInstance(Employee.class));
		
		return employees;
	}
	
	public int deleteEmployee(int id) {
		String sql = "DELETE FROM TBL_EMPLOYEES WHERE ID = ?";
		try {
			jdbcTemplate.update(sql, id);
			return 1;
		}
		catch (Exception e) {
			System.out.println("Failed");
			return -1;
		}
		
		
	}
}
