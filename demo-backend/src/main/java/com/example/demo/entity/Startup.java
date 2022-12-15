package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="startup")
public class Startup implements Serializable{

	private static final long serialVersionUID = -2109100350354791063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_startup", nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_business_sector",nullable=false)
	private BusinessSector idBusinessSector;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_startup_state",nullable=false)
	private StartupState idStartupState;
	
	@NotNull
	@Column(name = "annual_invoicing", nullable = false)
	private Integer anualInvoicing;
	
	@NotNull
	@Column(name = "fundation_year", nullable = false)
	private Date fundationYear;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_entrepreneur",nullable=false)
	private Entrepreneur idEntrepreneur;

	public Startup(@NotNull String name, @NotNull String email, @NotNull String description,
			@NotNull BusinessSector idBusinessSector, @NotNull StartupState idStartupState,
			@NotNull Integer anualInvoicing, @NotNull Date fundationYear, @NotNull Entrepreneur idEntrepreneur) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
		this.idBusinessSector = idBusinessSector;
		this.idStartupState = idStartupState;
		this.anualInvoicing = anualInvoicing;
		this.fundationYear = fundationYear;
		this.idEntrepreneur = idEntrepreneur;
	}

	public Startup() {
		
	}
	

}
