package com.example.teste.login.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "cdUsers")
@Entity(name = "tb_users")
public class Users implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdUsers;

	private String name;

	private String email;

	private String password;

	private LocalDate birthDate;

	private Perfil perfil;
	
	public Users() {
	}
	
	public Users(Long cdUsers, String name, String email, String password, LocalDate birthDate, Perfil perfil) {
		super();
		this.cdUsers = cdUsers;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.perfil = perfil;
	}

	public Users(String email, String password, Perfil perfil) {
		this.email = email;
		this.password = password;
		this.perfil = perfil;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.perfil == Perfil.ADMIN) return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
        else return List.of(new SimpleGrantedAuthority("EMPLOYEE"));
    }

    @Override
    public String getUsername() {
        return email;
    }
    
	@Override
	public String getPassword() {
		return password;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
