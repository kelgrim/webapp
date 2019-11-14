package ekn.learning.webapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ekn.learning.webapp.model.CalculatorModel;

@RestController
public class MathController {
	 
	 @RequestMapping(value = "/math" , method = RequestMethod.GET, params = {"type", "a"})
		public CalculatorModel doMath( @RequestParam(
				value="type", 
				defaultValue="square") String type, 
				
				@RequestParam(value="a", 
				defaultValue = "1") Integer a){
			
			CalculatorModel calc = new CalculatorModel(type);
			calc.doCalc(type, a);
			return calc;
			
		}
		
		@RequestMapping(value = "/math" , method = RequestMethod.GET, params = {"type", "a", "b"})
		public CalculatorModel doMath( @RequestParam(
				value="type", 
				defaultValue="add") String type, 
				
				@RequestParam(value="a", 
				defaultValue = "1") Integer a,
				
				@RequestParam(value="b") Integer b){
			
			
			CalculatorModel calc = new CalculatorModel(type);
			//if (b == null) calc.doCalc(type, a);
			//else 
			calc.doCalc(type, a, b);
			
			return calc;
			
		}
}
