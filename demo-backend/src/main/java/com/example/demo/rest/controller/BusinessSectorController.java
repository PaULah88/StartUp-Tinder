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

import com.example.demo.dto.BusinessSectorDTO;
import com.example.demo.service.IBusinessSectorService;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(BusinessSectorController.REQUEST_MAPPING)
public class BusinessSectorController {

	public static final String REQUEST_MAPPING = "businessSector";
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSectorController.class);

	@Autowired
	private IBusinessSectorService businessSectorService;


	//Devuelve los businesSectors
	@GetMapping(path = "/getBusinessSectors")
	public @ResponseBody List<BusinessSectorDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return businessSectorService.findAll();
	}
}