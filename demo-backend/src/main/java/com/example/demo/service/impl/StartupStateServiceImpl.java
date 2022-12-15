package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StartupStateDTO;
import com.example.demo.dto.mapper.StartupStateMapper;
import com.example.demo.entity.StartupState;
import com.example.demo.repository.StartupStateRepository;
import com.example.demo.service.IStartupStateService;

@Service
public class StartupStateServiceImpl implements IStartupStateService {

	@Autowired
	private StartupStateRepository startupStateRepository;

	@Override
	public List<StartupStateDTO> findAll() {
		List<StartupState> startupStateList = (List<StartupState>) startupStateRepository.findAll();
		return StartupStateMapper.INSTANCE.startupStateToStartupStateDtoList(startupStateList);
	}
}
