package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BusinessSectorDTO;
import com.example.demo.dto.mapper.BusinessSectorMapper;
import com.example.demo.entity.BusinessSector;
import com.example.demo.repository.BusinessSectorRepository;
import com.example.demo.service.IBusinessSectorService;


@Service
public class BusinesSectorServiceImpl implements IBusinessSectorService {

	
	@Autowired
	private BusinessSectorRepository businesSectorRepository;
	
	@Override
	public List<BusinessSectorDTO> findAll() {
		List<BusinessSector> bussinesSectorList = (List<BusinessSector>) businesSectorRepository.findAll();
		return BusinessSectorMapper.INSTANCE.businessSectorToBusinessSectorDtoList(bussinesSectorList);
	}

	
}


