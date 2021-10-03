package co.com.appgate.restcalculator.config;

import java.util.Collections;
import java.util.List;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import co.com.appgate.restcalculator.domain.model.OperatorsArray;

public class TokenCacheInfoRedisSerializer extends Jackson2JsonRedisSerializer<OperatorsArray> {

	/**
	 * Instantiates a new token cache info redis serializer.
	 * @param type the type
	 */
	public TokenCacheInfoRedisSerializer(Class<OperatorsArray> type) {

		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer#
	 * deserialize(byte[])
	 */
	@Override
	public OperatorsArray deserialize(byte[] bytes) {

		try {
			return super.deserialize(bytes);

		} catch (SerializationException se) {

			List<Integer> EmptyList = Collections.<Integer>emptyList();
			// String value = new StringRedisSerializer().deserialize(bytes);
			return new OperatorsArray(null, EmptyList);
		}
	}

}