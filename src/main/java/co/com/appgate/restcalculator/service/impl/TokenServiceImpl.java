package co.com.appgate.restcalculator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.repository.TokenDao;
import co.com.appgate.restcalculator.service.TokenServiceInfo;


@Service
public class TokenServiceImpl implements TokenServiceInfo{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public boolean saveToken(String token) {
		// TODO Auto-generated method stub
		return tokenDao.saveToken(token);
	}

	@Override
	public  List<List<Integer>> fetchAll() {
		
	return tokenDao.fetchAll();
	}
}
