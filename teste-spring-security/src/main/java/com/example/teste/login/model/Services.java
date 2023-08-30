package com.example.teste.login.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "cdService")
@Entity(name = "tb_services")
public class Services implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdService;

	private String nmService;

	private String dscService;

	private int durationService;

	private BigDecimal priceService;

	@ManyToMany(mappedBy = "services")
	@ToString.Exclude
	private List<Employees> employees;

	@ManyToOne
	@JoinColumn(name = "cd_salon")
	private Salons salons;

}
