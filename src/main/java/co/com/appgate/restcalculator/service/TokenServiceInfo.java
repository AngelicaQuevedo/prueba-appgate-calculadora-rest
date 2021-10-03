package co.com.appgate.restcalculator.service;

import java.util.List;
import java.util.Optional;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;

public interface TokenServiceInfo {
	

	List<OperatorsArray> fetchAll();

	boolean saveToken(String name, String token);

	Optional<OperatorsArray> fetchUserById(String id);

	boolean updateEntity(String string, String string2, OperatorsArray operatorsw);
	
	boolean removeValuesToken(String name);

}
