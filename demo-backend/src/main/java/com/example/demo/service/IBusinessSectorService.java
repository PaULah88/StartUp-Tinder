package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BusinessSectorDTO;

public interface IBusinessSectorService{

	List<BusinessSectorDTO> findAll();
}