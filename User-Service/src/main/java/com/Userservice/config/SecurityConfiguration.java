package com.Userservice.config;

import com.Userservice.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

	private final UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return email-> userRepository.findByEmail(email).map(
				user-> User.builder()
						.username(user.getEmail())
						.password(user.getPassword())
						.roles(user.getRole().name())
						.build()
		)
				.orElseThrow(()->
						new UsernameNotFoundException(
								"User not found: " + email
						));
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers(HttpMethod.POST,"/users").permitAll()
						.requestMatchers(HttpMethod.POST,"/users/logedIn").permitAll()
						.requestMatchers(HttpMethod.GET,"/users").hasRole("Admin")
						.anyRequest().authenticated()
				)
				.authenticationProvider(authenticationProvider())
				.httpBasic(Customizer.withDefaults())
				.formLogin(form->form.disable());
		return http.build();

	}

}
