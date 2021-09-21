package co.com.appgate.restcalculator.domain.model;

import java.util.List;

public interface TokenServiceInfo {
	

	List<OperatorsArray> fetchAll();

	boolean saveToken(String name, String token);

	OperatorsArray fetchUserById(String id);

	boolean updateEntity(String string, String string2, OperatorsArray operatorsw);
	
	boolean removeValuesToken(String name);

}
