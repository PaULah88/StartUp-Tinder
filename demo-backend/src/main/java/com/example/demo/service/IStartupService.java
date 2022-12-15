package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.StartupDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IStartupService {

	StartupDTO getStartup(Integer id);

	DataSourceRESTResponse<List<StartupDTO>> getStartups(AnyPageFilter pageFilter);

	StartupDTO createStartup(StartupDTO createStartupRequest);

	Integer deleteStartup(Integer id);

	List<StartupDTO> findAll();

	Integer editStartup(StartupDTO editStartupRequest);

}