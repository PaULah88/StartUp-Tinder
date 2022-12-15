package com.example.demo.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="investment_range")
public class RangeInvester implements Serializable{

	private static final long serialVersionUID = -8791975826149815740L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_investment_range", nullable = false)
	private Integer id;
	
	@Column(name="value", nullable = false)
	@NotNull
	private String value;
	
	@OneToMany(mappedBy = "idInvesterRange")
	private Set<Invester> investers= new LinkedHashSet<>();
	
}
