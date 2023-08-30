package com.example.teste.login.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.teste.login.model.Users;
import com.example.teste.login.repository.UsersRepository;

@Service
public class AuthorizationService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
	
	private final UsersRepository UsersRepository;
	
	public AuthorizationService(UsersRepository UsersRepository) {
		this.UsersRepository = UsersRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = UsersRepository.findByEmail(username);
		
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email  não cadastrado");
		}
		logger.info("User found: " + username);
		return user;
	}

}
