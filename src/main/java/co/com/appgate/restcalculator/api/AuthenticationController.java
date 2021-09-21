package co.com.appgate.restcalculator.api;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.appgate.restcalculator.config.security.AuthenticationException;
import co.com.appgate.restcalculator.config.security.TokenUtil;
import co.com.appgate.restcalculator.config.security.dto.TokenRequest;
import co.com.appgate.restcalculator.config.security.dto.TokenResponse;
import co.com.appgate.restcalculator.domain.exception.UserAlreadyLogedException;
import co.com.appgate.restcalculator.domain.model.OperatorsArray;
import co.com.appgate.restcalculator.domain.model.TokenServiceInfo;

/**
 * Authentication controller jwt
 * @version 1.0, 18/09/21
 * @author Angélica Quevedo
 */

@RestController
public class AuthenticationController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil jwtTokenUtil;
	
	@Autowired
	private TokenServiceInfo tokenServiceInfo;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	
	private static final String ALREADY_LOGGED = "the user is already logged in, try with another user";
	private static final String USER_DISABLED = "USER_DISABLED";
	private static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
	
	

	/**
	 * @param authenticationRequest
	 * @return ResponseEntity
	 * @throws AuthenticationException
	 * @throws UserAlreadyLogedException
	 */
	@RequestMapping(value = "${get.token.path}", method = RequestMethod.POST, headers = "X-API-VERSION=1")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest)
			throws AuthenticationException, UserAlreadyLogedException{

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		if (jwtTokenUtil.validateIfUserHasActiveSession(userDetails.getUsername())!=null) {
			
			throw new UserAlreadyLogedException(ALREADY_LOGGED); 
			
		}
		
		tokenServiceInfo.saveToken(userDetails.getUsername(),token);
		
		
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	@ExceptionHandler({ UserAlreadyLogedException.class })
	private ResponseEntity<String> handleAuthenticationExceptionA(UserAlreadyLogedException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	/**
	 * @param username
	 * @param password
	 */
	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException(USER_DISABLED, e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException(INVALID_CREDENTIALS, e);
		}
	}
	
	@GetMapping("/info")
	public List<OperatorsArray>  fetchInfo(){
		List<OperatorsArray> intArray;
		intArray= tokenServiceInfo.fetchAll();
		
		
		return intArray;
		
		
	}
}
