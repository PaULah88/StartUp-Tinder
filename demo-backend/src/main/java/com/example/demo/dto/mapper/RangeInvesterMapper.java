package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.RangeInvesterDTO;
import com.example.demo.entity.RangeInvester;

@Mapper
public interface RangeInvesterMapper {

	RangeInvesterMapper INSTANCE = Mappers.getMapper(RangeInvesterMapper.class);

	List<RangeInvesterDTO> rangeInvesterToRangeInvesterDtoList(List<RangeInvester> rangeInvestors);
}