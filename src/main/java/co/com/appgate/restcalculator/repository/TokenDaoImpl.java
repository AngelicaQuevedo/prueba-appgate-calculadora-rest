package co.com.appgate.restcalculator.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import co.com.appgate.restcalculator.beans.OperatorsArray;
import co.com.appgate.restcalculator.exception.UserAlreadyLogedException;

@Repository
public class TokenDaoImpl implements TokenDao {

	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String KEY = "TOKEN";
	
	

	@Override
	public boolean saveToken(String name, String token) {
		try {
			List<Integer> aList = new ArrayList<>();
			redisTemplate.opsForHash().put(KEY, name, new OperatorsArray(token, aList));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public  List<OperatorsArray> fetchAll() {
		
		
		//List<List<Integer>>  intArraydos = redisTemplate.opsForHash().values(KEY).getClass();
		
		List<OperatorsArray> pepe = redisTemplate.opsForHash().values(KEY);
		
		
		
		//Arrays.asList(pepe).stream().forEach(System.out::println);
		
		//System.out.println("pepepepepe"+ redisTemplate.opsForHash().keys(KEY).toString());
		
		

		//for (OperatorsArray f : pepe) {
		    //System.out.println("pip: " + f.getOperators());
		    
		  //  for (Integer c : f) {
		  //  }
		//}
		
		return pepe;
	}


	@Override
	public OperatorsArray fetchUserById(String id) {
		// TODO Auto-generated method stub
		
		OperatorsArray arrayToken = null;
		

		arrayToken = (OperatorsArray) redisTemplate.opsForHash().get(KEY,id);
		
		//System.out.println("este es aqui "+ arrayToken.toString());
		

		
        return arrayToken;
	}


	@Override
	public boolean update(String string, String string2, OperatorsArray operatorsArray) {
		
		//List<Integer> aList = new ArrayList<>();
		//aList.addAll(operatorsArray);
	    try {
            redisTemplate.opsForHash().put(string, string2, operatorsArray);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

}
