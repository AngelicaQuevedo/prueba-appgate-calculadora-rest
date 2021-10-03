package co.com.appgate.restcalculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.domain.model.UserOperationResult;
import co.com.appgate.restcalculator.repository.UserResultRepository;

@Service
public class UserOperationServiceImpl implements UserOperationService{
	
	@Autowired
	UserResultRepository repository;
	
	public void crearOperationResult (UserOperationResult result) {
			
		 repository.save(result);
	}
	
	public void deleteAllPrevioudResults (List<UserOperationResult> result) {
		
		 repository.deleteAll(result);
	}

	public List<UserOperationResult> getByName (String userName) {
		return repository.findByName(userName);

	}

}
