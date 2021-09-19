package co.com.appgate.restcalculator.repository;

import java.util.List;

public interface TokenDao {

	boolean saveToken(String token);

	List<List<Integer>> fetchAll();

}
