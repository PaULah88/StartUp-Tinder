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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.StartupDTO;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IStartupService;
import com.example.demo.utils.Constant;

import lombok.extern.java.Log;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(StartupController.REQUEST_MAPPING)
public class StartupController {

	public static final String REQUEST_MAPPING = "startup";
	private static final Logger LOGGER = LoggerFactory.getLogger(StartupController.class);

	@Autowired
	private IStartupService startupService;

	//Crear
	@PostMapping(path = "/createStartup")
	public ResponseEntity<?> createStartup(@Valid @RequestBody StartupDTO createStartupRequest,
			BindingResult result) {
		LOGGER.info("createStartup in progress...");
		StartupDTO startupNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.STARTUP_CREATE_SUCCESS;
		if (!result.hasErrors()) {
			try {
				startupNew = startupService.createStartup(createStartupRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				if (e.getMostSpecificCause().getMessage().contains(Constant.STARTUP_NAME_REQUIRED)) {
					message = Constant.STARTUP_NAME_REQUIRED;
					status = HttpStatus.OK;
				} else {
					message = Constant.DATABASE_QUERY_ERROR;
					status = HttpStatus.BAD_REQUEST;
				}

				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			}
			response.put("startup", startupNew);
		} else {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.STARTUP_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}

		LOGGER.info("saveStartup is finished...");
		response.put(Constant.MESSAGE, message);

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	//modificar
	@PostMapping(path = "/editStartup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('STARTUPS')")
	public ResponseEntity<?> editStartup(@Valid @RequestBody StartupDTO editStartupRequest, BindingResult result) {
		LOGGER.info("editStartup in progress...");
		int id = 0;
		StartupDTO startupOlder = startupService.getStartup(editStartupRequest.getId());
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.STARTUP_EDIT_SUCCESS;
		if(startupOlder!=null) {
			if(!result.hasErrors()) {
				try {
					id = startupService.editStartup(editStartupRequest);
					response.put("startupId", id);
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				}catch (DataAccessException e) {
					if(e.getMostSpecificCause().getMessage().contains(Constant.STARTUP_EMAIL_ERROR)) {
						message = Constant.STARTUP_EMAIL_ERROR;
						status= HttpStatus.OK;
					}else {
						message = Constant.DATABASE_QUERY_ERROR;
						status= HttpStatus.BAD_REQUEST;
					}
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
					response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				}
				
			}else {
				List<String> errors = new ArrayList<>();
				for(FieldError error : result.getFieldErrors()) {
					errors.add(error.getDefaultMessage());
				}
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
				message = Constant.STARTUP_NOT_EDIT;
				response.put(Constant.ERROR, errors);
				status = HttpStatus.OK;
			}
		}else {
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			message = Constant.ID_NOT_EXISTS;
			status = HttpStatus.BAD_REQUEST;
		}
			

		
		response.put(Constant.MESSAGE, message);
		LOGGER.info("editStartup is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);
	
	}

	//eliminar
	@DeleteMapping("/deleteStartup")
	@PreAuthorize("hasAnyAuthority('STARTUPS')")
	public ResponseEntity<?> deleteStartup(@RequestParam(value = "id")Integer id) {
		LOGGER.info("deleteStartup in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.STARTUP_DELETE_SUCCESS;
		try {
			startupService.deleteStartup(id);
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
		} catch (DataAccessException e) {
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			status = HttpStatus.BAD_REQUEST;
			message = Constant.STARTUP_NOT_DELETE;
		} 
		response.put(Constant.MESSAGE, message);
		LOGGER.info("deleteStartup is finished...");
		return new ResponseEntity<Map<String, Object>>(response,status);
	}
	
	//Devuelve startups pageable
	@PostMapping(path = "/getStartups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('STARTUPS')")
	public @ResponseBody DataSourceRESTResponse<List<StartupDTO>> getStartups(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getStartups in progress...");
		DataSourceRESTResponse<List<StartupDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = startupService.getStartups(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		} 
		LOGGER.info("getStartups is finished...");
		return dres;
	}
	
	//Devuelve los startups
	@GetMapping(path = "/getStartups")
	@PreAuthorize("hasAnyAuthority('STARTUPS')")
	public @ResponseBody List<StartupDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return startupService.findAll();
	}

	//Buscar por id
	@GetMapping("/getStartup")
	@PreAuthorize("hasAnyAuthority('STARTUPS')")
	public ResponseEntity<?> getStartup(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getStartup in progress...");
		StartupDTO startup = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			startup = startupService.getStartup(id);
			if (startup == null) {
				response.put(Constant.MESSAGE, Constant.STARTUP_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<StartupDTO>(startup, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("getStartup is finished...");
		return re;
	}

}