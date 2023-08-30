package com.example.teste.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teste.login.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Users findByEmail(String email);
}
