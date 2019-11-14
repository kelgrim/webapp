package ekn.learning.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseEntityTests {

	@RequestMapping(value = "/responseentities")
	public ResponseEntity getResponse() {
		//return new ResponseEntity<>("Hello World!", HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok("Hello World!");
	}
}
