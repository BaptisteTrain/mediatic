package fr.dta.mediatic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import fr.dta.mediatic.model.Role;
import fr.dta.mediatic.model.Usr;

@Component
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UsrService userService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usr> option = Optional.of(userService.getUserByLogin(login));
		if (option.isPresent()) {
			
			Usr user = option.get();
			List<GrantedAuthority> rules = this.getUserCredentials(user);
			
			return new User(user.getLogin(), user.getPassword(), rules);
		}
		
		throw new UsernameNotFoundException("username.not.found");
	}
	
	public List<GrantedAuthority> getUserCredentials(Usr user) {
		// completer les roles
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		autorities.add(new SimpleGrantedAuthority(role.toString()));
		return autorities;
	}
}
