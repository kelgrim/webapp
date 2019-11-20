package ekn.learning.webapp.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTest {

	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("BEFORE IS BEING RUN");
	    RestAssured.port = port;
	}
	
	@Test
	public void testExample() {
		System.out.println("test executing");
		System.out.println("----- Server running on port: " + port);
		//assertTrue(false);
		get("/api/v1/user").then()
		.assertThat().body("id", hasItems(1,2,3));
		
	}
}
