package com.sec.ToDoApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(customizable -> customizable.disable())
		.authorizeHttpRequests(request -> request.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User
				.withDefaultPasswordEncoder()
				.username("mahesh")
				.password("123")
				.roles("USER")
				.build();
		
		
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public AuthenticationProvider authrnticatioProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
}



//http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin(form -> form.permitAll()).logout(logout -> logout.permitAll());


//http.authorizeHttpRequests(authorize -> authorize
//.requestMatchers("/").permitAll()
//.requestMatchers("/todoApp/userdata", "/apps").hasAuthority("ADMIN")
//.requestMatchers("/myapp").hasAuthority("CLIENT")
//.anyRequest().authenticated())
//.formLogin(login -> login.loginPage("/").usernameParameter("email").defaultSuccessUrl("/", true).permitAll())
//.logout(logout -> logout.logoutUrl("/logout").permitAll());


