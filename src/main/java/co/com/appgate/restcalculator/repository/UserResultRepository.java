package co.com.appgate.restcalculator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.appgate.restcalculator.domain.model.UserOperationResult;

public interface UserResultRepository extends MongoRepository<UserOperationResult, String> {
	
	public List<UserOperationResult> findByName (String name);

}