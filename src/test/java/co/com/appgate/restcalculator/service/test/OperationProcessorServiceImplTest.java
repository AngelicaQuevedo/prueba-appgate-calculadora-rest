package co.com.appgate.restcalculator.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import co.com.appgate.restcalculator.config.security.TokenUtil;
import co.com.appgate.restcalculator.domain.model.OperationType;
import co.com.appgate.restcalculator.domain.model.OperatorsArray;
import co.com.appgate.restcalculator.service.OperationProcessorService;
import co.com.appgate.restcalculator.service.TokenServiceInfo;
import co.com.appgate.restcalculator.service.UserOperationService;

@SpringBootTest
class OperationProcessorServiceImplTest {

	@Autowired
	OperationProcessorService operationProcessorService;

	@MockBean
	TokenUtil jwtTokenUtil;

	@Autowired
	TokenServiceInfo tokenServiceInfo;

	@MockBean
	UserOperationService userOperation;
	
	
	HttpServletRequest request;

	@BeforeEach
	public void setUp() {
		
		request = mock(HttpServletRequest.class);
		
	}

	@Test
	public void addOperatorStrategyOne() {
		
		//Filled list of operands and takes into account the previous result
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		int[] ints = {1,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
		operationProcessorService.addingOperatorBySpecificStrategy(4, true, request);
		
		assertEquals(tokenServiceInfo.fetchUserById("username").get().getOperators(), Arrays.asList(1,2,3,4,null));
	}

	@Test
	public void addOperatorStrategyTwo() {
		
		//List full of operands does not take into account the above result
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		int[] ints = {1,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
		operationProcessorService.addingOperatorBySpecificStrategy(5, false, request);
		
		assertEquals(tokenServiceInfo.fetchUserById("username").get().getOperators(), Arrays.asList(1,2,3,5));
	}

	@Test
	public void addOperatorStrategyThree() {
		
		//Empty list of operands does not take into account the previous result.
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		Optional<OperatorsArray> dataRedis = Optional.empty();
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
		operationProcessorService.addingOperatorBySpecificStrategy(10, false, request);
		
		assertEquals(tokenServiceInfo.fetchUserById("username").get().getOperators(), Arrays.asList(10));
	}
	
	
	@Test
	public void addOperatorStrategyFour() {
		
		//Empty list of operands, takes into account the previous result
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		Optional<OperatorsArray> dataRedis = Optional.empty();
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
		operationProcessorService.addingOperatorBySpecificStrategy(18, true, request);
		
		assertEquals(tokenServiceInfo.fetchUserById("username").get().getOperators(), Arrays.asList(18,0));
	}
	
	@Test
	public void processOperationSum() {
		
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		int[] ints = {8,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
        
		
		
		assertEquals(operationProcessorService.proceed(OperationType.sum, request), 13);
	
	}
	
	@Test
	public void processOperationMultiplication() {
		
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		int[] ints = {2,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
        
	
		
		assertEquals(operationProcessorService.proceed(OperationType.multiplication, request), 12);
	
	}
	
	
	@Test
	public void processOperationSubtraction() {
		
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWI"));
		int[] ints = {8,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
        
		
		
		assertEquals(operationProcessorService.proceed(OperationType.subtraction, request), 3);
	
	}
	
	@Test
	public void processOperationPotentiation() {
		
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciJ9.eyJzdWI"));
		int[] ints = {2,2,3};
		List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
		Optional<OperatorsArray> dataRedis = Optional.of(new OperatorsArray(anyString(), list));
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
        
		assertEquals(operationProcessorService.proceed(OperationType.potentiation, request), 64);
	
	}
	
	@Test
	public void processOperationEmptyList() {
		
		when(jwtTokenUtil.getnameFromRequest(request)).thenReturn(new Pair<String, String>("username" ,"Bearer eyJhbGciJ9.eyJzdWI"));
		Optional<OperatorsArray> dataRedis = Optional.empty();
		when(jwtTokenUtil.fetchUserInformation("username")).thenReturn(dataRedis);
        
		
		   assertThrows(IllegalArgumentException.class,
		            ()->{
		            	operationProcessorService.proceed(OperationType.potentiation, request);
		            });
	}

}
