package co.com.appgate.restcalculator.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import co.com.appgate.restcalculator.domain.model.OperationResponse;
import co.com.appgate.restcalculator.domain.model.OperationType;

public interface OperationProcessorService {

	public void addingOperatorBySpecificStrategy(Integer operator, Boolean isAddingpreviousResult,
			HttpServletRequest request);

	public Integer proceed(OperationType operation, HttpServletRequest request);
	
}
