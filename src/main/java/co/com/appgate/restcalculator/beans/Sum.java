package co.com.appgate.restcalculator.beans;

import java.util.List;

public class Sum implements IOperationAdapter {

	@Override
	public Integer execute(List<Integer> operators) {
		return operators.stream().mapToInt(a -> a).sum();
	}



}
