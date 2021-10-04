package co.com.appgate.restcalculator.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.appgate.restcalculator.config.security.dto.TokenUserDetails;

/**
 * In Memory service data
 * @version 1.0, 19/09/21
 * @author Angélica Quevedo
 */

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

	static List<TokenUserDetails> volatileUserList = new ArrayList<>();
	private static final String USER_NOT_FOUND = "USER_NOT_FOUND '%s'.";

	static {
		volatileUserList.add(new TokenUserDetails(1L, "angelica",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "appgate",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "test",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "goku",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "goten",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "gohan",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
		volatileUserList.add(new TokenUserDetails(2L, "roshi",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_2"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TokenUserDetails> findFirst = volatileUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format(USER_NOT_FOUND, username));
		}

		return findFirst.get();
	}

}
