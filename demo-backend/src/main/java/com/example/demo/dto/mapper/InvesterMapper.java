package com.example.demo.dto.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.InvesterDTO;
import com.example.demo.entity.Invester;

@Mapper
public interface InvesterMapper {

	InvesterMapper INSTANCE = Mappers.getMapper(InvesterMapper.class);

    InvesterDTO investerToInvesterDto(Invester investor);

    List<InvesterDTO> investerToInvesterDtoList(List<Invester> investors);

    Invester investerDTOtoInvester(InvesterDTO investerDTO);

}