package co.com.appgate.restcalculator.beans;

import java.util.List;

public class ConcreteFactory {
	

	public static IOperationAdapter getOperation(OperationType opeType ) {
		
		switch(opeType) {
		
		case sum:
			return new Sum();
			
		case subtraction:
			return new Subtraction();
		
		case multiplication:
			return new Multiplication();
						
		case potentiation:
			return new Potentiation();
		default:
			throw new IllegalArgumentException("Not supported");
		
			
		}
	
		
	}

}
