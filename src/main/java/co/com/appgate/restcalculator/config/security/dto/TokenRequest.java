package co.com.appgate.restcalculator.config.security.dto;

import java.io.Serializable;

/**
 * TokenRequest object
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

public class TokenRequest implements Serializable {

	private static final long serialVersionUID = -5616176897013108345L;

	private String username;
	private String password;

	public TokenRequest() {
		super();
	}

	public TokenRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
