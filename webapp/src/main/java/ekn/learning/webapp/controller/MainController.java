package ekn.learning.webapp.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.DefaultModel;
import ekn.learning.webapp.model.GreetingModel;
import ekn.learning.webapp.model.Quote;
import ekn.learning.webapp.services.external.RestConsumer;

@RestController
public class MainController {
	 private static final String template = "Hello, %s!";
	 private final AtomicLong counter = new AtomicLong();
	 
	 @Autowired
	 private RestConsumer restConsumer;
	 
	@RequestMapping()
	public List showLocations() {
		return new DefaultModel().getLocations();
	}
	 
	@RequestMapping(value = "/greeting" , method = RequestMethod.GET)
	public GreetingModel greeting(
			@RequestParam(value="name", 
			defaultValue="World") String name){
		
		return new GreetingModel(counter.incrementAndGet(), String.format(template, name));
	}
	
	//TODO: Make own controller for the external api's
	@RequestMapping(value="/random")
	public Quote getQuote() {
		return restConsumer.getRandomQuote();
	}
}
