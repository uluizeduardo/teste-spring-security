package com.example.teste.login.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecutiryConfiguration {

	private static final String[] PUBLIC = { "/auth/login", "/auth/register"};
	
	private static final String[] ADMIN = { "/auth/**"};
	
	private static final String[] EMPLOYEE = { "/product"};
	
	private static final String[] CLIENT = { "/auth/"};
	
	private final SecurityFilter securityFilter;
	
	public SecutiryConfiguration(SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}

	@Bean // Bean responsável por liberar acesso as urls de acordo com as permissões
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf((csrf)  -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                		.requestMatchers(HttpMethod.POST, PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.GET, EMPLOYEE).hasRole("EMPLOYEE")
                        .requestMatchers(ADMIN).hasRole("ADMIN")
                        .anyRequest().authenticated()
          ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
