package co.com.appgate.restcalculator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.config.security.TokenUtil;
import co.com.appgate.restcalculator.domain.factory.FactoryOperation;
import co.com.appgate.restcalculator.domain.factory.IOperationAdapter;
import co.com.appgate.restcalculator.domain.model.OperationResponse;
import co.com.appgate.restcalculator.domain.model.OperationType;
import co.com.appgate.restcalculator.domain.model.OperatorsArray;
import co.com.appgate.restcalculator.domain.model.UserOperationResult;

/**
 * @author angelicaquevedo
 *
 */
@Service
public class OperationProcessorServiceImpl implements OperationProcessorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private TokenServiceInfo tokenServiceInfo;

	@Autowired
	private UserOperationService userOperation;

	private static final String KEY = "TOKEN";

	public void addingOperatorBySpecificStrategy(Integer operator, Boolean isAddingpreviousResult,
			HttpServletRequest request) {

		Pair<String, String> data = jwtTokenUtil.getnameFromRequest(request);
		Optional<OperatorsArray> dataRedis = jwtTokenUtil.fetchUserInformation(data.getValue0());
		Integer lastResult = null;

		if (!dataRedis.isEmpty() && !isAddingpreviousResult) {

			List<Integer> operatorsList = new ArrayList<Integer>(dataRedis.get().getOperators());
			operatorsList.add(operator);
			OperatorsArray newEntityRedis = new OperatorsArray(data.getValue1(), operatorsList);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedis);

		}

		if (dataRedis.isEmpty() && !isAddingpreviousResult) {

			List<Integer> operatorNewList = new ArrayList<Integer>();
			operatorNewList.add(operator);
			OperatorsArray newEntityRedisEmpty = new OperatorsArray(data.getValue1(), operatorNewList);
			tokenServiceInfo.updateEntity(KEY, data.getValue0(), newEntityRedisEmpty);

		}

		if (dataRedis.isEmpty() && isAddingpreviousResult) {

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

		}

		if (!dataRedis.isEmpty() && isAddingpreviousResult) {

			List<Integer> operatorsList = new ArrayList<Integer>(dataRedis.get().getOperators());
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

		}

	}

	public Integer proceed(OperationType operation, HttpServletRequest request) {

		Pair<String, String> data = jwtTokenUtil.getnameFromRequest(request);
		Optional<OperatorsArray> dataForActiveSesion = jwtTokenUtil.fetchUserInformation(data.getValue0());
		List<Integer> operatorsList;
		if (dataForActiveSesion.isPresent()) {
			operatorsList = new ArrayList<Integer>(dataForActiveSesion.get().getOperators());
			logger.info("the list of operators for the user is as follows: [{}]", operatorsList);
		} else {
			throw new IllegalArgumentException("it is necessary to include at least one operand");
		}
		IOperationAdapter selectedOperation = FactoryOperation.getOperation(operation);
		logger.info("The class of the operator is: [{}]", selectedOperation.getClass());

		UserOperationResult user = new UserOperationResult();
		user.setName(data.getValue0());
		user.setResult(selectedOperation.execute(operatorsList));
		userOperation.deleteAllPrevioudResults(userOperation.getByName(data.getValue0()));
		userOperation.crearOperationResult(user);

		tokenServiceInfo.removeValuesToken(data.getValue0());

		return selectedOperation.execute(operatorsList);

	}

}
