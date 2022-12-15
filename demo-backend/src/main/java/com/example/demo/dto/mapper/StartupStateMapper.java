package com.example.demo.dto.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.StartupStateDTO;
import com.example.demo.entity.StartupState;

@Mapper
public interface StartupStateMapper {

	StartupStateMapper INSTANCE = Mappers.getMapper(StartupStateMapper.class);

	List<StartupStateDTO> startupStateToStartupStateDtoList(List<StartupState> startupStates);
}