package co.com.appgate.restcalculator.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
//${v1API}
@RequestMapping(path = "rest-calculator")




public class CalculatorController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenUtil jwtTokenUtil;
	
	@Autowired
	private TokenServiceInfo tokenServiceInfo;
	
	
	
	@GetMapping(path = "/add-operators/{operator}/{previousResult}", headers = "X-API-VERSION=1")
	public Integer addingOperator(@PathVariable Integer operator,@PathVariable Boolean previousResult) {
		
		OperatorsArray pepe = jwtTokenUtil.validateIfUserHasActiveSession("lole");
		
		if (pepe==null) {
			
			System.out.println("es nullo");
			 
			 
		}else
	    System.out.println("no es nullo");
		System.out.println("esto es el operators" + pepe);
		String token = pepe.getToken();
	    System.out.println("Token del controlador "+ token);

	    List<Integer> operatorsw = new ArrayList<Integer>(pepe.getOperators());
	    
	    System.out.println("esta es la lista"+ operatorsw);
		
		operatorsw.add(operator);
		
		OperatorsArray listas= new OperatorsArray(token,operatorsw);
		System.out.println("username este es "+ jwtTokenUtil.getUsernameFromToken(token));
		
		tokenServiceInfo.updateEntity("TOKEN","lole",listas);
		
		
		return operator+1292939393;
		
	}
	
	@GetMapping(path = "/execute-operation/{operation}", headers = "X-API-VERSION=1")
	public String addingOperator(@PathVariable String operation, HttpServletRequest request) throws ExpiredJwtException {
		
		String token= request.getHeader("Authorization");
		
		String username = "angelica";
		
		
		OperatorsArray pepe = jwtTokenUtil.validateIfUserHasActiveSession("angelica");
		
	
		
		
		/*String jwtToken = null;
		if (token != null && token.startsWith("Bearer ")) {
			System.out.println("entre");
			jwtToken = token.substring(7);
			System.out.println(jwtToken);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				System.out.println(username);
			} catch (IllegalArgumentException e) {
				logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
			} 
		
			return username;
		
	}
		return username;
	
	}*/
		return username;
		
	}

}
