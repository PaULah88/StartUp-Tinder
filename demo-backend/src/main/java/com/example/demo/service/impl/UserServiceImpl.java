package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserStartupDTO;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.dto.mapper.UserStartupMapper;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.AbstractDemoService;
import com.example.demo.service.IUserService;


@Service
public class UserServiceImpl extends AbstractDemoService implements IUserService {

	/**
	 * Especificación JPA para {@link User}.
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserStartupDTO getUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		return UserStartupMapper.INSTANCE.userToUserDto(user);
	}

	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<UserDTO>> getUsers(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<User> users = SpecificationBuilder.selectDistinctFrom(userRepository).where(pageFilter)
				.findAll(pageFilter);
		DataSourceRESTResponse<List<UserDTO>> datares = new DataSourceRESTResponse<>();
		List<UserDTO> usersDTO = UserMapper.INSTANCE.userToUserDtoList(users.getContent());
		datares.setTotalElements((int) users.getTotalElements());
		datares.setData(usersDTO);
		return datares;
	}

	@Override
	@Transactional
	public UserDTO createUser(UserDTO createUserRequest) {
		User user = UserMapper.INSTANCE.userDTOtoUser(createUserRequest);
		User newUser = userRepository.save(user);
		return UserMapper.INSTANCE.userToUserDto(newUser);
	}

	@Override
	@Transactional
	public Integer deleteUser(Integer id) {
		userRepository.deleteById(id);
		return id;

	}

	@Override
	public List<UserDTO> findAll() {
		List<User> userList = (List<User>) userRepository.findAll();
		return UserMapper.INSTANCE.userToUserDtoList(userList);
	}

	@Override
	public Integer editUser(UserDTO editUserRequest) {
		User mappedUser = UserMapper.INSTANCE.userDTOtoUser(editUserRequest);
		User editUser = userRepository.save(fromEditUserRequest(mappedUser));
		return editUser.getId();
	}

}
