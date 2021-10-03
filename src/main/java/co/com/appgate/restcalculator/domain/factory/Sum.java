package co.com.appgate.restcalculator.domain.factory;

import java.util.List;

public class Sum implements IOperationAdapter {

	
	@Override
	public Integer execute(List<Integer> operators) {
		
	if (operators.isEmpty()) {
			
			return 0; 
			
		}
		return operators.stream().mapToInt(a -> a).sum();
	}
}
