package co.com.appgate.restcalculator.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;

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

		List<OperatorsArray> allRowRetrived = redisTemplate.opsForHash().values(KEY);
	
		return allRowRetrived;
	}


	@Override
	public Optional<OperatorsArray> fetchUserById(String id) {

		OperatorsArray arrayToken = null;
		arrayToken = (OperatorsArray) redisTemplate.opsForHash().get(KEY,id);

        return Optional.ofNullable(arrayToken);
	}


	@Override
	public boolean update(String string, String string2, OperatorsArray operatorsArray) {
		
	    try {
            redisTemplate.opsForHash().put(string, string2, operatorsArray);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}


	@Override
	public boolean remove(String name) {
		
		try {
            redisTemplate.opsForHash().delete(KEY, name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

	}

}
