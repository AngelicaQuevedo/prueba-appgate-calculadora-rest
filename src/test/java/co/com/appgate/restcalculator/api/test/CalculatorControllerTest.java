package co.com.appgate.restcalculator.api.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import co.com.appgate.restcalculator.api.CalculatorController;
import co.com.appgate.restcalculator.domain.model.OperationType;
import co.com.appgate.restcalculator.service.OperationProcessorService;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {
	
	@Autowired
	CalculatorController calculatorController;

	HttpServletRequest request;

	@BeforeEach
	public void setUp() {

		request = mock(HttpServletRequest.class);
	}
	
	@Test
	void contextLoads() {}

	@Test
	public void AddingOperandCall() throws Exception {

		OperationProcessorService spy = mock(OperationProcessorService.class);
		spy.addingOperatorBySpecificStrategy(null, null, request);
		
		verify(spy, times(1)).addingOperatorBySpecificStrategy(null, null, request);

	}

	@Test
	public void PerformingOperation() throws Exception {

		OperationProcessorService spy = mock(OperationProcessorService.class);
		spy.proceed(OperationType.subtraction, request);
		
		verify(spy, times(1)).proceed(OperationType.subtraction, request);

	}

}
