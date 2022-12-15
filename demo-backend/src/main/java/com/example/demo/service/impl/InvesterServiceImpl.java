package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.dto.InvesterDTO;
import com.example.demo.dto.mapper.InvesterMapper;
import com.example.demo.entity.Invester;
import com.example.demo.repository.InvesterRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.AbstractDemoService;
import com.example.demo.service.IInvesterService;

@Service
public class InvesterServiceImpl extends AbstractDemoService implements IInvesterService {

	@Autowired
	private InvesterRepository investerRepository;

	@Override
	public InvesterDTO getInvester(Integer id) {
		Invester invester = investerRepository.findById(id).orElse(null);
		return InvesterMapper.INSTANCE.investerToInvesterDto(invester);
	}

	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<InvesterDTO>> getInvesters(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Invester> investers = SpecificationBuilder.selectDistinctFrom(investerRepository).where(pageFilter)
				.findAll(pageFilter);
		DataSourceRESTResponse<List<InvesterDTO>> datares = new DataSourceRESTResponse<>();
		List<InvesterDTO> contactsDTO = InvesterMapper.INSTANCE.investerToInvesterDtoList(investers.getContent());
		datares.setTotalElements((int) investers.getTotalElements());
		datares.setData(contactsDTO);
		return datares;
	}

	@Override
	@Transactional
	public InvesterDTO createInvester(InvesterDTO createInvesterRequest) {
		Invester invester = InvesterMapper.INSTANCE.investerDTOtoInvester(createInvesterRequest);
		Invester newInvester = investerRepository.save(invester);
		return InvesterMapper.INSTANCE.investerToInvesterDto(newInvester);
	}

	@Override
	@Transactional
	public Integer deleteInvester(Integer id) {
		investerRepository.deleteById(id);
		return id;

	}

	@Override
	public List<InvesterDTO> findAll() {
		List<Invester> investerList = (List<Invester>) investerRepository.findAll();
		return InvesterMapper.INSTANCE.investerToInvesterDtoList(investerList);
	}

	@Override
	public Integer editInvester(InvesterDTO editInvesterRequest) {
		Invester mappedInvester = InvesterMapper.INSTANCE.investerDTOtoInvester(editInvesterRequest);
		Invester editInvester = investerRepository.save(fromEditInvesterRequest(mappedInvester));
		return editInvester.getId();
	}
}
