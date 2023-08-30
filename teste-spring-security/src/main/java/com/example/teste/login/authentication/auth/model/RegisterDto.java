package com.example.teste.login.authentication.auth.model;

import java.time.LocalDate;

import com.example.teste.login.model.Perfil;

public record RegisterDto(String name, String username, String password, LocalDate birthDate, String cpf, Perfil perfil) {}
