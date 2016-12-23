package fr.dta.mediatic;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

/*@Component
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserList userList;

	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		Optional<User> option = Optional.of(userList.getUserByLogin(login));
		
		if(option.isPresent()) {
			User user = option.get();
			List<UserAuthority> rules = this.getUserCredentials(user);
			BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
			String encodedPass = passEncoder.encode(user.getPassword());
			return new org.springframework.security.core.userdetails.User(user.getLogin(), encodedPass, rules);
		}
		
		throw new UsernameNotFoundException("username.not.found");
	}

	private List<UserAuthority> getUserCredentials(User user) {
		return user.getRoles();
	}
	
}*/
