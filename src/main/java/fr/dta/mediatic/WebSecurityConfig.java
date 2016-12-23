package fr.dta.mediatic;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			// Dossiers sans authentification:
			.and()
			.authorizeRequests()
			.antMatchers("/api/public/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			//
			.and()
			.httpBasic()
			.and()
			.csrf()
			.disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
	}
}*/
