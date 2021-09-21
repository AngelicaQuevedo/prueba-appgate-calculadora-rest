package co.com.appgate.restcalculator.domain.exception;


public class UserAlreadyLogedException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyLogedException(String message) {
		super(message);
	}
}
