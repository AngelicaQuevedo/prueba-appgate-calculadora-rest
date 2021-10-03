package co.com.appgate.restcalculator.domain.factory;

import java.util.List;

public class Subtraction implements IOperationAdapter {

	@Override
	public Integer execute(List<Integer> operators) {
		
	if (operators.isEmpty()) {
			
			return 0; 
			
		}
		Integer min = operators.get(0);
		for (int i = 1; i < operators.size(); i++) {
			min -= operators.get(i);
		}
		return min;
	}

}
