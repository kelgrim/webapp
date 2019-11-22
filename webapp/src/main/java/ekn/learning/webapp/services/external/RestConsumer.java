package ekn.learning.webapp.services.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ekn.learning.webapp.model.Quote;

@Service
public class RestConsumer {

	RestTemplate restTemplate = new RestTemplate();
	
	/*@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	} */
	
	public Quote getRandomQuote() {
		return  restTemplate.getForObject(
				"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	}
}
