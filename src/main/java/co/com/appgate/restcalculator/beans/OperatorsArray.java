package co.com.appgate.restcalculator.beans;

import java.io.Serializable;
import java.util.List;

public class OperatorsArray implements Serializable {
	
	private static final long serialVersionUID = 7156526077883281623L;
	
	private List<Integer> operators;
	private String  token;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public OperatorsArray(String token,List<Integer> operators) {
		super();
		this.operators = operators;
		this.token = token;
	}

	public List<Integer> getOperators() {
		return operators;
	}

	public void setOperators(List<Integer> operators) {
		this.operators = operators;
	}

	@Override
	public String toString() {
		return "OperatorsArray [operators=" + operators + ", token=" + token + "]";
	}


	
	
	

}
