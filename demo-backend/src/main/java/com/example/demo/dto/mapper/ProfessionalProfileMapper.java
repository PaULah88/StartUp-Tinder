package com.example.demo.dto.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ProfessionalProfileDTO;
import com.example.demo.entity.ProfessionalProfile;

@Mapper
public interface ProfessionalProfileMapper {

	ProfessionalProfileMapper INSTANCE = Mappers.getMapper(ProfessionalProfileMapper.class);

	List<ProfessionalProfileDTO> professionalProfileToProfessionalProfileDtoList(List<ProfessionalProfile> professionalProfiles);
}