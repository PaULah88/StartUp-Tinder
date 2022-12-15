package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RangeInvesterDTO {

	private Integer id;
	
	@NotNull
	private String value;
		
}
