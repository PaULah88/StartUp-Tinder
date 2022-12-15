package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EntrepreneurDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 7666094310092518378L;

	private Integer id;
	
	@Size(max = 50)
	@NotNull
	private String firstName;
	
	@Size(max = 50)
	@NotNull
	private String lastName;
	
	@Size(max = 50)
	@NotNull
	private String email;
	
	@Size(max = 50)
	@NotNull
	private ProfessionalProfileDTO idProfessionalProfile;
	
	@Size(max = 100)
	@NotNull
	private String linkedinProfile;

}
