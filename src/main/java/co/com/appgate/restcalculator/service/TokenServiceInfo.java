package co.com.appgate.restcalculator.service;

import java.util.List;

import co.com.appgate.restcalculator.beans.OperatorsArray;

public interface TokenServiceInfo {
	

	List<OperatorsArray> fetchAll();

	boolean saveToken(String name, String token);

	OperatorsArray fetchUserById(String id);

	boolean updateEntity(String string, String string2, OperatorsArray operatorsw);

}
