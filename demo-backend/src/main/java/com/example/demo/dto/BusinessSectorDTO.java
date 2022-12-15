package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BusinessSectorDTO {
	
	private Integer id;

	@NotNull
	private String type;
	
}
