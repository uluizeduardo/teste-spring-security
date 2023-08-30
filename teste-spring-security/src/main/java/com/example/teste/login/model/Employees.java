package com.example.teste.login.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cdEmployee")
@Entity(name = "tb_employees")
public class Employees implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdEmployee;
	
	@OneToOne
	private Users user;

    @Column(unique = true)
    private String phone;

    @ManyToMany(mappedBy = "employees")
    @ToString.Exclude
    private List<Salons> salons = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_employee_service",
            joinColumns = @JoinColumn(name = "cd_employee"),
            inverseJoinColumns = @JoinColumn(name = "cd_service")
    )
    @ToString.Exclude
    private List<Services> services = new ArrayList<>();
	

}
