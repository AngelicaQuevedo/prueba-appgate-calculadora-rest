package co.com.appgate.restcalculator.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appgate.restcalculator.config.security.TokenUtil;
import co.com.appgate.restcalculator.domain.model.FactoryOperation;
import co.com.appgate.restcalculator.domain.model.IOperationAdapter;
import co.com.appgate.restcalculator.domain.model.OperationResponse;
import co.com.appgate.restcalculator.domain.model.OperationType;
import co.com.appgate.restcalculator.domain.model.OperatorsArray;
import co.com.appgate.restcalculator.domain.model.TokenServiceInfo;
import co.com.appgate.restcalculator.domain.model.UserOperationResult;
import co.com.appgate.restcalculator.domain.model.UserOperationService;
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
	private TokenUtil jwtTokenUtil;

	@Autowired
	private TokenServiceInfo tokenServiceInfo;

	@Autowired
	private UserOperationService userOperation;

	FactoryOperation adapt;

	private static final String KEY = "TOKEN";

	@GetMapping(path = "/add-operators/{operator}/{previousResult}", headers = "X-API-VERSION=1")
	public ResponseEntity<String> addingOperator(@PathVariable Integer operator, @PathVariable Boolean previousResult,
			HttpServletRequest request) throws ExpiredJwtException {

		Pair<String, String> data = getnameFromRequest(request);
		OperatorsArray dataRedis = jwtTokenUtil.validateIfUserHasActiveSession(data.getValue0());
		Integer lastResult = null;

		if (dataRedis != null && !previousResult) {

			List<Integer> operatorsList = new ArrayList<Integer>(dataRedis.getOperators());
			operatorsList.add(operator);
			OperatorsArray newEntityRedis = new OperatorsArray(data.getValue1(), operatorsList);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedis);

			return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);

		}

		if (dataRedis == null && !previousResult) {

			List<Integer> operatorNewList = new ArrayList<Integer>();
			operatorNewList.add(operator);
			OperatorsArray newEntityRedisEmpty = new OperatorsArray(data.getValue1(), operatorNewList);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedisEmpty);

			return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);
		}

		if (dataRedis == null && previousResult) {

			List<Integer> operatorNewListTrue = new ArrayList<Integer>();

			List<UserOperationResult> mainUserData = userOperation.getByName(data.getValue0());

			for (UserOperationResult o : mainUserData) {
				lastResult = o.getResult();

			}

			if (lastResult == null) {

				lastResult = 0;
			}

			operatorNewListTrue.add(operator);
			operatorNewListTrue.add(lastResult);
			OperatorsArray newEntityRedis = new OperatorsArray(data.getValue1(), operatorNewListTrue);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedis);

			return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);
		}

		if (dataRedis != null && previousResult) {

			List<Integer> operatorsList = new ArrayList<Integer>(dataRedis.getOperators());
			List<UserOperationResult> mainUserData = userOperation.getByName(data.getValue0());

			if (mainUserData != null) {

				for (UserOperationResult o : mainUserData) {
					lastResult = o.getResult();

				}

			} else {
				lastResult = 0;
			}

			operatorsList.add(operator);
			operatorsList.add(lastResult);

			OperatorsArray newEntityRedis = new OperatorsArray(data.getValue1(), operatorsList);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedis);

			return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);

		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED.toString(), HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/execute-operation/{operation}", headers = "X-API-VERSION=1")
	public ResponseEntity<OperationResponse> addingOperator(@PathVariable OperationType operation,
			HttpServletRequest request) throws ExpiredJwtException, IllegalArgumentException {

		Pair<String, String> data = getnameFromRequest(request);
		OperatorsArray dataForActiveSesion = jwtTokenUtil.validateIfUserHasActiveSession(data.getValue0());
		List<Integer> operatorsList;
		if (dataForActiveSesion!=null) {
		 operatorsList = new ArrayList<Integer>(dataForActiveSesion.getOperators());
		}
		else {
			throw new IllegalArgumentException("it is necessary to include at least one operator");
		}
		IOperationAdapter selectedOperation = FactoryOperation.getOperation(operation);
		logger.info("The class of the operator is: [{}]", selectedOperation.getClass());

		UserOperationResult user = new UserOperationResult();
		user.setName(data.getValue0());
		user.setResult(selectedOperation.execute(operatorsList));
		userOperation.deleteAllPrevioudResults(userOperation.getByName(data.getValue0()));
		userOperation.crearOperationResult(user);

		tokenServiceInfo.removeValuesToken(data.getValue0());
		return ResponseEntity.ok(new OperationResponse(selectedOperation.execute(operatorsList)));

	}

	private Pair<String, String> getnameFromRequest(HttpServletRequest request) {

		String tokenFromRequest = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (tokenFromRequest != null && tokenFromRequest.startsWith("Bearer ")) {
			jwtToken = tokenFromRequest.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.error("UNABLE_TO_GET_USERNAME", e);
			}
		}
		Pair<String, String> tupla = new Pair<String, String>(username, jwtToken);
		return tupla;

	}
}
