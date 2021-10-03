package co.com.appgate.restcalculator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;
import co.com.appgate.restcalculator.repository.TokenDao;



@Service
public class TokenServiceImpl implements TokenServiceInfo{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public boolean saveToken(String name, String token) {

		return tokenDao.saveToken(name, token);
	}

	@Override
	public  List<OperatorsArray> fetchAll() {
		
	return tokenDao.fetchAll();
	}
	
	@Override
	public Optional<OperatorsArray> fetchUserById(String id) {
	return tokenDao.fetchUserById(id);
	    }

	@Override
	public boolean updateEntity(String string, String string2, OperatorsArray operatorsArray) {
		
		return tokenDao.update(string,string2,operatorsArray);
		
	}

	@Override
	public boolean removeValuesToken(String name) {
		
		return tokenDao.remove(name);
	}


}
