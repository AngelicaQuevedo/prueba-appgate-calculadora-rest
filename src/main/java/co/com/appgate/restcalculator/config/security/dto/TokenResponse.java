package co.com.appgate.restcalculator.config.security.dto;

import java.io.Serializable;


/**
 * TokenResponse object
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String token;

	public TokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}