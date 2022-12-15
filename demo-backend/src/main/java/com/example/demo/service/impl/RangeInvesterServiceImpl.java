package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RangeInvesterDTO;
import com.example.demo.dto.mapper.RangeInvesterMapper;
import com.example.demo.entity.RangeInvester;
import com.example.demo.repository.RangeInvesterRepository;
import com.example.demo.service.IRangeInvesterService;


@Service
public class RangeInvesterServiceImpl implements IRangeInvesterService {

	
	@Autowired
	private RangeInvesterRepository rangeRepository;
	
	@Override
	public List<RangeInvesterDTO> findAll() {
		List<RangeInvester> rangeInvesterList = (List<RangeInvester>) rangeRepository.findAll();
		return RangeInvesterMapper.INSTANCE.rangeInvesterToRangeInvesterDtoList(rangeInvesterList);
	}

	
}