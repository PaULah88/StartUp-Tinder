package com.example.demo.dto;

import javax.validation.constraints.NotNull;

import com.example.demo.utils.Constant;

import lombok.Data;

@Data
public class UserStartupDTO {

	private Integer id;

	private String nif;

	private String name;

	private String surname1;

	private String surname2;

	@NotNull(message = Constant.NIF_REQUIRED)
	private String login;

	@NotNull(message = Constant.PASSWORD_REQUIRED)
	private String password;
	
}
