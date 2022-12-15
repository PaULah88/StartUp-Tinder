package com.example.demo.dto.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.StartupDTO;
import com.example.demo.entity.Startup;

@Mapper
public interface StartupMapper {

	StartupMapper INSTANCE = Mappers.getMapper(StartupMapper.class);

    StartupDTO startupToStartupDto(Startup startup);

    List<StartupDTO> startupToStartupDtoList(List<Startup> startups);

    Startup startupDTOtoStartup(StartupDTO startupDTO);

}