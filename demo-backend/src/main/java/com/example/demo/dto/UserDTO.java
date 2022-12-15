package com.example.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.example.demo.entity.Profile;
import com.example.demo.utils.Constant;

import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -2605882025118929733L;

	private Integer id;

	private String nif;

	private String name;

	private String surname1;

	private String surname2;

	@NotNull(message = Constant.NIF_REQUIRED)
	private String login;

	@NotNull(message = Constant.PASSWORD_REQUIRED)
	private String password;

	private Set<Profile> profiles = new HashSet<>();
}
