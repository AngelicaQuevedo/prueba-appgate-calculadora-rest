package co.com.appgate.restcalculator.beans;

import java.util.List;

public class Potentiation implements IOperationAdapter {

	@Override
	public Integer execute(List<Integer> operators) {
		
		Integer total = operators.get(0);

		
		  for (var f=1;f<operators.size();f++){
		      total = (int)Math.pow(total,operators.get(f));
		    }
		  return total;
	}


	
	

}
