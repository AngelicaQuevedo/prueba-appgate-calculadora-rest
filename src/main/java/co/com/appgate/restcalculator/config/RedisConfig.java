package co.com.appgate.restcalculator.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;
/**
 * Configurations to redis
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

@Configuration
@EnableCaching
public class RedisConfig {

	/**
	 * @return JedisConnectionFactory
	 */
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("127.0.0.1");
		redisStandaloneConfiguration.setPort(6379);
		
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectionFactory;
		
				
	}
	
	/**
	 * @return RedisTemplate<String,Object> 
	 */
	public RedisTemplate<String,Object> redisTemplate(){
		
		 RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		 redisTemplate.setConnectionFactory(jedisConnectionFactory());
		 redisTemplate.setKeySerializer(new StringRedisSerializer());
		 redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		 redisTemplate.setValueSerializer(tokenCacheInfoSerializer());
		 redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

		 redisTemplate.setEnableTransactionSupport(true);
		 redisTemplate.afterPropertiesSet();
		return redisTemplate;
		
		
	}

	private Jackson2JsonRedisSerializer<OperatorsArray> tokenCacheInfoSerializer() {
		return new TokenCacheInfoRedisSerializer(OperatorsArray.class);
	}
	
	
	
}
