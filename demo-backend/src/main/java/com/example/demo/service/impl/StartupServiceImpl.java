package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.StartupDTO;
import com.example.demo.dto.mapper.StartupMapper;
import com.example.demo.entity.Startup;
import com.example.demo.repository.StartupRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.AbstractDemoService;
import com.example.demo.service.IStartupService;

@Service
public class StartupServiceImpl extends AbstractDemoService implements IStartupService {

	@Autowired
	private StartupRepository startupRepository;

	@Override
	public StartupDTO getStartup(Integer id) {
		Startup startup = startupRepository.findById(id).orElse(null);
		return StartupMapper.INSTANCE.startupToStartupDto(startup);
	}

	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<StartupDTO>> getStartups(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Startup> startups = SpecificationBuilder.selectDistinctFrom(startupRepository).where(pageFilter)
				.findAll(pageFilter);
		DataSourceRESTResponse<List<StartupDTO>> datares = new DataSourceRESTResponse<>();
		List<StartupDTO> contactsDTO = StartupMapper.INSTANCE.startupToStartupDtoList(startups.getContent());
		datares.setTotalElements((int) startups.getTotalElements());
		datares.setData(contactsDTO);
		return datares;
	}

	@Override
	@Transactional
	public StartupDTO createStartup(StartupDTO createStartupRequest) {
		Startup startup = StartupMapper.INSTANCE.startupDTOtoStartup(createStartupRequest);
		Startup newStartup = startupRepository.save(startup);
		return StartupMapper.INSTANCE.startupToStartupDto(newStartup);
	}

	@Override
	@Transactional
	public Integer deleteStartup(Integer id) {
		startupRepository.deleteById(id);
		return id;

	}

	@Override
	public List<StartupDTO> findAll() {
		List<Startup> startupList = (List<Startup>) startupRepository.findAll();
		return StartupMapper.INSTANCE.startupToStartupDtoList(startupList);
	}

	@Override
	public Integer editStartup(StartupDTO editStartupRequest) {
		Startup mappedStartup = StartupMapper.INSTANCE.startupDTOtoStartup(editStartupRequest);
		Startup editStartup = startupRepository.save(fromEditStartupRequest(mappedStartup));
		return editStartup.getId();
	}
}
