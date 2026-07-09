package com.Userservice.config;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers(HttpMethod.POST,"/users").permitAll()
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(form->form.disable());
		return http.build();

	}

}
