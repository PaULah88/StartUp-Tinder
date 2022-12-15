package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.InvesterDTO;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IInvesterService {

	InvesterDTO getInvester(Integer id);
	DataSourceRESTResponse<List<InvesterDTO>> getInvesters(AnyPageFilter pageFilter);
	InvesterDTO createInvester(InvesterDTO createInvesterRequest);
	Integer deleteInvester(Integer id);
	List<InvesterDTO> findAll();
	Integer editInvester(InvesterDTO editInvesterRequest);
	
	  
}