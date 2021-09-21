package co.com.appgate.restcalculator.repository;

import java.util.List;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;

public interface TokenDao {

	boolean saveToken(String name,String token);

	List<OperatorsArray> fetchAll();

	OperatorsArray fetchUserById(String id);

	boolean update(String string, String string2, OperatorsArray operatorsArray);

	boolean remove(String name);



}
