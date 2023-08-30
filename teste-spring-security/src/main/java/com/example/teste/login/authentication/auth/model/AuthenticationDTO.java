package com.example.teste.login.authentication.auth.model;

import java.time.LocalDate;

import com.example.teste.login.model.Perfil;

public record AuthenticationDTO(String username, String password, String name, LocalDate birthDate, String cpf, Perfil perfil) {}
