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
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "professional_profile")
public class ProfessionalProfile implements Serializable{
	
	private static final long serialVersionUID = 2778249209842697525L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professional_profile", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "type_professional_profile", nullable = false, length = 50)
    private String typeProfessionalProfile;

    @OneToMany(mappedBy = "idProfessionalProfile")
    private Set<Entrepreneur> entrepreneurs = new LinkedHashSet<>();

}