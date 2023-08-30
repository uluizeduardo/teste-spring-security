package com.example.teste.login.model;

public enum Perfil {
	
	ADMIN(0, "ADMIN"),
	EMPLOYEE(1, "EMPLOYEE"),
	CLIENT(2, "CLIENT");

	private long cod;
	private String desc;

	private Perfil(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
}
