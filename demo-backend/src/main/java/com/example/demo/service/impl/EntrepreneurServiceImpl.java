package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EntrepreneurDTO;
import com.example.demo.dto.mapper.EntrepreneurMapper;
import com.example.demo.entity.Entrepreneur;
import com.example.demo.repository.EntrepreneurRepository;
import com.example.demo.service.AbstractDemoService;
import com.example.demo.service.IEntrepreneurService;

@Service
public class EntrepreneurServiceImpl extends AbstractDemoService implements IEntrepreneurService {

	@Autowired
	private EntrepreneurRepository entrepreneurRepository;
	
	@Override
	public List<EntrepreneurDTO> findAll() {
		List<Entrepreneur> entrepreneurList = (List<Entrepreneur>) entrepreneurRepository.findAll();
		return EntrepreneurMapper.INSTANCE.entrepreneurToEntrepreneurDtoList(entrepreneurList);
	}



}