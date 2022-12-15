package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.RangeInvesterDTO;

public interface IRangeInvesterService{

	List<RangeInvesterDTO> findAll();
}