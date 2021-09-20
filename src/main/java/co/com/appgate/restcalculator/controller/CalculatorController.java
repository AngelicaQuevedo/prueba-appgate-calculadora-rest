package co.com.appgate.restcalculator.controller;

import java.util.ArrayList;
import java.util.List;

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

import co.com.appgate.restcalculator.beans.ConcreteFactory;
import co.com.appgate.restcalculator.beans.IOperationAdapter;
import co.com.appgate.restcalculator.beans.OperationResponse;
import co.com.appgate.restcalculator.beans.OperationType;
import co.com.appgate.restcalculator.beans.OperatorsArray;
import co.com.appgate.restcalculator.security.TokenUtil;
import co.com.appgate.restcalculator.service.TokenServiceInfo;
import io.jsonwebtoken.ExpiredJwtException;


/**
 * Main controller for receiving requests to  generate calculations
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

@RestController
@RequestMapping(path = "rest-calculator")
public class CalculatorController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenUtil jwtTokenUtil;
	
	@Autowired
	private TokenServiceInfo tokenServiceInfo;
	
	
	ConcreteFactory adapt;
	
	
	@GetMapping(path = "/add-operators/{operator}/{previousResult}", headers = "X-API-VERSION=1")
	public  ResponseEntity<String>  addingOperator(@PathVariable Integer operator,@PathVariable Boolean previousResult) {
		
		OperatorsArray pepe = jwtTokenUtil.validateIfUserHasActiveSession("juan");

	    System.out.println("no es nullo");
		System.out.println("esto es el operators" + pepe);
		String token = pepe.getToken();
	    System.out.println("Token del controlador "+ token);

	    List<Integer> operatorsw = new ArrayList<Integer>(pepe.getOperators());
	    
	    System.out.println("esta es la lista"+ operatorsw);
		
		operatorsw.add(operator);
		
		String username= null;
		OperatorsArray listas= new OperatorsArray(token,operatorsw);
	
		
		tokenServiceInfo.updateEntity("TOKEN","juan",listas);
		
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(path = "/execute-operation/{operation}", headers = "X-API-VERSION=1")
	public ResponseEntity<OperationResponse> addingOperator(@PathVariable OperationType operation, HttpServletRequest request) throws ExpiredJwtException ,IllegalArgumentException {
		
       OperatorsArray pepe = jwtTokenUtil.validateIfUserHasActiveSession("juan");
    
       List<Integer> operatorsw = new ArrayList<Integer>(pepe.getOperators());
       

       IOperationAdapter operationdos = ConcreteFactory.getOperation(operation);
       
       System.out.println(operationdos.getClass());
       
      System.out.println( "Resultado" + operationdos.execute(operatorsw));
      
      
		return ResponseEntity.ok(new OperationResponse(operationdos.execute(operatorsw)));
		
	}
	


}
