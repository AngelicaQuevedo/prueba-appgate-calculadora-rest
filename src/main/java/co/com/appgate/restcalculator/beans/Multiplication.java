package co.com.appgate.restcalculator.beans;

import java.util.List;

public class Multiplication implements IOperationAdapter {



	@Override
	public Integer execute(List<Integer> operators) {
		
		Integer total = 1;

	
		  for (var f=0;f<operators.size();f++){
		      total = (operators.get(f)*total);
		    }
		  return total;
		
	}

}
