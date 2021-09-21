package co.com.appgate.restcalculator.config.security;


/**
 * customized exception AuthenticationException
 * @version 1.0, 19/09/21
 * @author Angélica Quevedo
 */
public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
