package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserStartupDTO;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IUserService;
import com.example.demo.utils.CipherUtils;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(UserController.REQUEST_MAPPING)
public class UserController {

	public static final String REQUEST_MAPPING = "user";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	// Crear
	@PostMapping(path = "/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO createUserRequest, BindingResult result) {
		LOGGER.info("saveUser in progress...");
		UserDTO userNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.USER_CREATE_SUCCESS;
		CipherUtils cipherUtils= new CipherUtils();
		if (!result.hasErrors()) {
			try {
				String passwordEncrypt = cipherUtils.encrypt(createUserRequest.getLogin(), createUserRequest.getPassword());
				createUserRequest.setPassword(passwordEncrypt);
				userNew = userService.createUser(createUserRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				if (e.getMostSpecificCause().getMessage().contains(Constant.LOGIN_REQUIRED)) {
					message = Constant.LOGIN_REQUIRED;
					status = HttpStatus.OK;
				} else {
					message = Constant.DATABASE_QUERY_ERROR;
					status = HttpStatus.BAD_REQUEST;
				}

				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			}
			response.put("user", userNew);
		} else {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.USER_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}

		LOGGER.info("saveUser is finished...");
		response.put(Constant.MESSAGE, message);

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	// Devuelve users pageable
	@PostMapping(path = "/getUsers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('USERS')")
	public @ResponseBody DataSourceRESTResponse<List<UserDTO>> getUsers(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getUsers in progress...");
		DataSourceRESTResponse<List<UserDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = userService.getUsers(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		}
		LOGGER.info("getUsers is finished...");
		return dres;
	}

	// Devuelve los users
	@GetMapping(path = "/getUsers")
	@PreAuthorize("hasAnyAuthority('USERS')")
	public @ResponseBody List<UserDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return userService.findAll();
	}

	// Buscar por id
	@GetMapping("/getUser")
	@PreAuthorize("hasAnyAuthority('USERS')")
	public ResponseEntity<?> getUser(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getUser in progress...");
		UserStartupDTO user = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			user = userService.getUser(id);
			if (user == null) {
				response.put(Constant.MESSAGE, Constant.INVESTER_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<UserStartupDTO>(user, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("getUser is finished...");
		return re;
	}

}