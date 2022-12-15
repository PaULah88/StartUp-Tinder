package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "startup_state")
public class StartupState implements Serializable {

    private static final long serialVersionUID = -6086539931419312468L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_startup_state")
	private Integer id;
	
	@Column(name="type_startup", nullable = false)
	@NotNull
	private String type;
	
	@OneToMany(mappedBy = "idStartupState")
	private Set<Startup> startups= new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "idStartUpState")
	private Set<Invester> investers= new LinkedHashSet<>();

}
