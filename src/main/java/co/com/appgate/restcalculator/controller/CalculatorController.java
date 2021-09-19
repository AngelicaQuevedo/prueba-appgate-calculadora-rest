package co.com.appgate.restcalculator.controller;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;


/**
 * Main controller for receiving requests to  generate calculations
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

@RestController
//${v1API}
@RequestMapping(path = "rest-calculator")

public class CalculatorController {
	
	
	@GetMapping(path = "/add-operators/{operator}/{previousResult}", headers = "X-API-VERSION=1")
	public Integer addingOperator(@PathVariable Integer operator,@PathVariable Boolean previousResult) {
		
		return operator+1292939393;
		
	}
	
	@GetMapping(path = "/execute-operation/{operation}", headers = "X-API-VERSION=1")
	public String addingOperator(@PathVariable String operation) throws ExpiredJwtException {
		
		return operation;
		
	}
	
	

}
