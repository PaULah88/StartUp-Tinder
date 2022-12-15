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

import com.example.demo.dto.StartupStateDTO;
import com.example.demo.service.IStartupStateService;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
@RequestMapping(StartupStateController.REQUEST_MAPPING)
public class StartupStateController {

	public static final String REQUEST_MAPPING = "startupState";
	private static final Logger LOGGER = LoggerFactory.getLogger(StartupStateController.class);

	@Autowired
	private IStartupStateService startupStateService;

	// Devuelve los startupStates
	@GetMapping(path = "/getStartupStates")
	public @ResponseBody List<StartupStateDTO> findAll() {
		LOGGER.info("findAll in progress...");
		return startupStateService.findAll();
	}
}