package co.com.appgate.restcalculator.service;

import java.util.List;

import co.com.appgate.restcalculator.domain.model.UserOperationResult;

public interface UserOperationService {
	
	public void crearOperationResult (UserOperationResult result);
	public void deleteAllPrevioudResults (List<UserOperationResult> result);
	public List<UserOperationResult> getByName (String userName);

}
