package com.example.teste.login.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.login.authentication.auth.model.AuthenticationDTO;
import com.example.teste.login.authentication.auth.model.LoginResponseDTO;
import com.example.teste.login.authentication.auth.model.RegisterDto;
import com.example.teste.login.authentication.config.TokenService;
import com.example.teste.login.model.Perfil;
import com.example.teste.login.model.Users;
import com.example.teste.login.repository.UsersRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	
	private final UsersRepository usersRepository;
	
	private final TokenService tokenService;
	
	public AuthenticationController(AuthenticationManager authenticationManager, UsersRepository usersRepository, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.usersRepository = usersRepository;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((Users) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterDto registerDto) {
		if (this.usersRepository.findByEmail(registerDto.username()) != null) {
			return ResponseEntity.badRequest().build();
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
		Users newUser = new Users(registerDto.username(), encryptedPassword, registerDto.perfil());

		this.usersRepository.save(newUser);
		
		if (registerDto.perfil().equals(Perfil.ADMIN)) {
			System.out.println("salvar um admin");
		} else if(registerDto.perfil().equals(Perfil.EMPLOYEE)) {
			System.out.println("salvar um employee");
		} else {
			System.out.println("salvar um user");
		}

		return ResponseEntity.ok().build();
	}

}
