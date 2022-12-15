package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.dto.InvesterDTO;
import com.example.demo.dto.InvesterDTO;
import com.example.demo.dto.InvesterDTO;
import com.example.demo.dto.mapper.InvesterMapper;
import com.example.demo.entity.Invester;
import com.example.demo.entity.enums.ResponseCodeEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IInvesterService;
import com.example.demo.utils.Constant;

import lombok.extern.java.Log;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(InvesterController.REQUEST_MAPPING)
public class InvesterController {

	public static final String REQUEST_MAPPING = "investor";
	private static final Logger LOGGER = LoggerFactory.getLogger(InvesterController.class);

	@Autowired
	private IInvesterService investorService;

	//Crear
	@PostMapping(path = "/createInvestor")
	public ResponseEntity<?> createInvestor(@Valid @RequestBody InvesterDTO createInvesterRequest,
			BindingResult result) {
		LOGGER.info("saveInvestor in progress...");
		InvesterDTO investerNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.INVESTER_CREATE_SUCCESS;
		if (!result.hasErrors()) {
			try {
				investerNew = investorService.createInvester(createInvesterRequest);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
			} catch (DataAccessException e) {
				if (e.getMostSpecificCause().getMessage().contains(Constant.INVESTER_NAME_REQUIRED)) {
					message = Constant.INVESTER_NAME_REQUIRED;
					status = HttpStatus.OK;
				} else {
					message = Constant.DATABASE_QUERY_ERROR;
					status = HttpStatus.BAD_REQUEST;
				}

				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			}
			response.put("investor", investerNew);
		} else {
			List<String> errors = new ArrayList<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.WARNING.getValue());
			message = Constant.INVESTER_NOT_CREATED;
			response.put(Constant.ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}

		LOGGER.info("saveInvestor is finished...");
		response.put(Constant.MESSAGE, message);

		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	//modificar
	@PostMapping(path = "/editInvestor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@PreAuthorize("hasAnyAuthority('INVESTORS')")
	public ResponseEntity<?> editInvestor(@Valid @RequestBody InvesterDTO editInvestorRequest, BindingResult result) {
		LOGGER.info("editInvestor in progress...");
		int id = 0;
		InvesterDTO investorOlder = investorService.getInvester(editInvestorRequest.getId());
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.INVESTER_EDIT_SUCCESS;
		if(investorOlder!=null) {
			if(!result.hasErrors()) {
				try {
					id = investorService.editInvester(editInvestorRequest);
					response.put("investorid", id);
					response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				}catch (DataAccessException e) {
					if(e.getMostSpecificCause().getMessage().contains(Constant.INVESTER_EMAIL_ERROR)) {
						message = Constant.INVESTER_EMAIL_ERROR;
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
				message = Constant.INVESTER_NOT_EDIT;
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
	@DeleteMapping("/deleteInvestor")
	@PreAuthorize("hasAnyAuthority('INVESTORS')")
	public ResponseEntity<?> deleteInvestor(@RequestParam(value = "id")Integer id) {
		LOGGER.info("deleteInvester in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.INVESTER_DELETE_SUCCESS;
		try {
			investorService.deleteInvester(id);
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
		} catch (DataAccessException e) {
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			status = HttpStatus.BAD_REQUEST;
			message = Constant.INVESTER_NOT_DELETE;
		} 
		response.put(Constant.MESSAGE, message);
		LOGGER.info("deleteInvestor is finished...");
		return new ResponseEntity<Map<String, Object>>(response,status);
	}
	
	//Devuelve inversores pageable
	@PostMapping(path = "/getInvestors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('INVESTORS')")
	public @ResponseBody DataSourceRESTResponse<List<InvesterDTO>> getInvestors(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getInvestors in progress...");
		DataSourceRESTResponse<List<InvesterDTO>> dres = new DataSourceRESTResponse<>();
		try {
			dres = investorService.getInvesters(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		} 
		LOGGER.info("getInvestors is finished...");
		return dres;
	}
	
	//Devuelve los inversores
	@GetMapping(path = "/getInvestors")
	@PreAuthorize("hasAnyAuthority('INVESTORS')")
	public @ResponseBody List<InvesterDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return investorService.findAll();
	}

	//Buscar por id
	@GetMapping("/getInvestor")
	@PreAuthorize("hasAnyAuthority('INVESTORS')")
	public ResponseEntity<?> getInvestor(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getInvestor in progress...");
		InvesterDTO investor = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?> re = null;
		try {
			investor = investorService.getInvester(id);
			if (investor == null) {
				response.put(Constant.MESSAGE, Constant.INVESTER_NOT_EXISTS);
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.OK.getValue());
				re = new ResponseEntity<InvesterDTO>(investor, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(Constant.RESPONSE_CODE, ResponseCodeEnum.KO.getValue());
			response.put(Constant.MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(Constant.ERROR, e.getMessage());
			re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("getInvestor is finished...");
		return re;
	}

}