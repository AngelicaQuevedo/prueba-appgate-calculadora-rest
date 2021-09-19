package co.com.appgate.restcalculator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.beans.OperatorsArray;
import co.com.appgate.restcalculator.repository.TokenDao;
import co.com.appgate.restcalculator.service.TokenServiceInfo;


@Service
public class TokenServiceImpl implements TokenServiceInfo{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public boolean saveToken(String name, String token) {
		// TODO Auto-generated method stub
		return tokenDao.saveToken(name, token);
	}

	@Override
	public  List<OperatorsArray> fetchAll() {
		
	return tokenDao.fetchAll();
	}
	
	@Override
	public OperatorsArray fetchUserById(String id) {
	return tokenDao.fetchUserById(id);
	    }

	@Override
	public boolean updateEntity(String string, String string2, OperatorsArray operatorsArray) {
		
		return tokenDao.update(string,string2,operatorsArray);
		
	}


}
