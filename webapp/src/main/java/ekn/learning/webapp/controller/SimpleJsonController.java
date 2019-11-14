package ekn.learning.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.Student;
import ekn.learning.webapp.repos.EmployeeJdbcRepository;

@RestController
@RequestMapping(value = "/simplejson", method=RequestMethod.POST)
public class SimpleJsonController {

	
	@RequestMapping("/student/{pathVariable}")
	public String getStudent(@PathVariable() String pathVariable,
			@RequestBody Student student) {
		try {
			System.out.println(pathVariable);
			return student.asString();
		}
		catch(Exception e) {
			return "No valid values for student provided. Send JSON with ID, NAME, EMAIL, MAJOR";
		}
	}	
}
