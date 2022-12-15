package com.example.demo.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RangeInvesterDTO;
import com.example.demo.service.IRangeInvesterService;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(RangeInvesterController.REQUEST_MAPPING)
public class RangeInvesterController {

	public static final String REQUEST_MAPPING = "rangeInvester";
	private static final Logger LOGGER = LoggerFactory.getLogger(RangeInvesterController.class);

	@Autowired
	private IRangeInvesterService rangeInvesterService;


	//Devuelve los rangeInvester
	@GetMapping(path = "/getRangeInvestors")
	public @ResponseBody List<RangeInvesterDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return rangeInvesterService.findAll();
	}
}