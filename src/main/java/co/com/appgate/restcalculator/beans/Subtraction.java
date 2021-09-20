package co.com.appgate.restcalculator.beans;

import java.util.List;

public class Subtraction implements IOperationAdapter {

	@Override
	public Integer execute(List<Integer> operators) {
		   
		Integer min = operators.get(0);
		   for(int i = 1; i < operators.size(); i++)
		    {
		        min -= operators.get(i);
		    }
		    return min;
	}

}
