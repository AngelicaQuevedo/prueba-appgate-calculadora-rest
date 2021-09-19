package co.com.appgate.restcalculator.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements TokenDao {

	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String KEY = "TOKEN";
	
	
	@Override
	public boolean saveToken(String token) {
		try {
			
			redisTemplate.opsForHash().put(KEY, token,  new List[0]);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public  List<List<Integer>> fetchAll() {
		List<List<Integer>>  intArray;
		intArray= redisTemplate.opsForHash().values(KEY);
		
		System.out.println("dddddd" + redisTemplate.opsForHash().values(KEY).getClass());
		
		for (List<Integer> i :intArray) {
		System.out.println("arrayyy" + i);
		}
		return intArray;
	}

}
