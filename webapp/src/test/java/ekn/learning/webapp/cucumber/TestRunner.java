package ekn.learning.webapp.cucumber;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources"},
		plugin = {"pretty", "html:target/cucumber/results"})
@SpringBootTest
public class TestRunner {

}
