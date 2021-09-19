package co.com.appgate.restcalculator.service;

import java.util.List;

public interface TokenServiceInfo {
	
	boolean saveToken (String token);

	List<List<Integer>> fetchAll();

}
