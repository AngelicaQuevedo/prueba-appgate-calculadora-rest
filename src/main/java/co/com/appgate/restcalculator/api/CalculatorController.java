package co.com.appgate.restcalculator.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appgate.restcalculator.domain.model.OperationResponse;
import co.com.appgate.restcalculator.domain.model.OperationType;
import co.com.appgate.restcalculator.service.OperationProcessorService;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * Main controller for receiving requests to generate calculations
 * 
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

@RestController
@RequestMapping(path = "rest-calculator")
public class CalculatorController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OperationProcessorService proccesor;

	
	@GetMapping(path = "/add-operators/{operator}/{isAddingpreviousResult}", headers = "X-API-VERSION=1")
	public ResponseEntity<String> addingOperand(@PathVariable Integer operator,
			@PathVariable Boolean isAddingpreviousResult, HttpServletRequest request) throws ExpiredJwtException {

		logger.trace("Starting the process to add an operator");
	     proccesor.addingOperatorBySpecificStrategy(operator, isAddingpreviousResult, request);
	     
	     return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/execute-operation/{operation}", headers = "X-API-VERSION=1")
	public ResponseEntity<OperationResponse> performOperation(@PathVariable OperationType operation,
			HttpServletRequest request) throws ExpiredJwtException, IllegalArgumentException {
		
		logger.trace("Starting operation execution process");
		Integer result = proccesor.proceed(operation, request);
		 
		 return ResponseEntity.ok(new OperationResponse(result));

	}

}
