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

import com.example.demo.dto.EntrepreneurDTO;
import com.example.demo.service.IEntrepreneurService;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(EntrepreneurController.REQUEST_MAPPING)
public class EntrepreneurController {

	public static final String REQUEST_MAPPING = "entrepreneur";
	private static final Logger LOGGER = LoggerFactory.getLogger(EntrepreneurController.class);

	@Autowired
	private IEntrepreneurService entrepreneurService;


	//Devuelve los entrepreneurs
	@GetMapping(path = "/getEntrepreneurs")
		public @ResponseBody List<EntrepreneurDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return entrepreneurService.findAll();
	}

}