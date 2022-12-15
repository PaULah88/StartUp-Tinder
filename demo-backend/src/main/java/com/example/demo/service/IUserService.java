package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserStartupDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IUserService{
	
	UserStartupDTO getUser(Integer id);
	DataSourceRESTResponse<List<UserDTO>> getUsers(AnyPageFilter pageFilter);
	UserDTO createUser(UserDTO createUserRequest);
	Integer deleteUser(Integer id);
	List<UserDTO> findAll();
	Integer editUser(UserDTO editUserRequest);
}
