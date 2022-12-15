package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfessionalProfileDTO {
    
	private Integer id;
	
    @Size(max = 50)
    @NotNull
    private String typeProfessionalProfile;
}