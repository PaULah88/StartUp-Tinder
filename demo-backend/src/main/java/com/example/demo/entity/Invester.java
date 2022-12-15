package com.example.demo.entity;

import java.io.Serializable;

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
@Table(name = "invester")
public class Invester implements Serializable{

	private static final long serialVersionUID = -216002265132757796L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_invester", nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_investment_range",nullable=false)
	private RangeInvester idInvesterRange;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_business_sector",nullable=false)
	private BusinessSector idBusinessSector;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_startup_state", nullable = false)
	private StartupState idStartUpState;

	public Invester(@NotNull String name, @NotNull String email, @NotNull RangeInvester idInvesterRange,
			@NotNull BusinessSector idBusinessSector, @NotNull StartupState idStartUpState) {
		super();
		this.name = name;
		this.email = email;
		this.idInvesterRange = idInvesterRange;
		this.idBusinessSector = idBusinessSector;
		this.idStartUpState = idStartUpState;
	}
	
	public Invester() {
	}
	
}
