package com.example.teste.login.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cdSalon")
@Entity(name = "tb_salon")
public class Salons implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdSalon;

    private String nmSalon;

    private String cnpj;

    private LocalTime openingTime;

    private LocalTime closingTime;

    //Todo: create routine to this atribute.
    private boolean isOpen;

    @ManyToMany
    @JoinTable(
            name = "tb_salon_employee",
            joinColumns = @JoinColumn(name = "cd_salon"),
            inverseJoinColumns = @JoinColumn(name = "cd_employee")
    )
    @ToString.Exclude
    private List<Employees> employees = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "cd_owner")
    private Owners owner;

    @OneToMany
    @ToString.Exclude
    private List<Services> services = new ArrayList<>();

}
