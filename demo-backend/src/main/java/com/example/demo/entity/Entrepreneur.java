package com.example.demo.entity;

import java.io.Serializable;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
@Entity
@Table(name = "entrepreneur")
public class Entrepreneur implements Serializable{

	private static final long serialVersionUID = 8540191650029893459L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entrepreneur", nullable = false)
	private Integer id;

	@Size(max = 50)
	@NotNull
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Size(max = 50)
	@NotNull
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Size(max = 50)
	@NotNull
	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_professional_profile", nullable = false)
	private ProfessionalProfile idProfessionalProfile;

	@Size(max = 100)
	@NotNull
	@Column(name = "linkedin_profile", nullable = false, length = 100)
	private String linkedinProfile;
	
	@OneToMany(mappedBy = "idEntrepreneur")
	private Set<Startup> entrepreneurs= new LinkedHashSet<>();

	public Entrepreneur(@Size(max = 50) @NotNull String firstName, @Size(max = 50) @NotNull String lastName,
			@Size(max = 50) @NotNull String email, @NotNull ProfessionalProfile idProfessionalProfile,
			@Size(max = 100) @NotNull String linkedinProfile, Set<Startup> entrepreneurs) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idProfessionalProfile = idProfessionalProfile;
		this.linkedinProfile = linkedinProfile;
		this.entrepreneurs = entrepreneurs;
	}
	
	public Entrepreneur() {};
}