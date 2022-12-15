package com.example.demo.dto.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.BusinessSectorDTO;
import com.example.demo.entity.BusinessSector;

@Mapper
public interface BusinessSectorMapper {

	BusinessSectorMapper INSTANCE = Mappers.getMapper(BusinessSectorMapper.class);

	List<BusinessSectorDTO> businessSectorToBusinessSectorDtoList(List<BusinessSector> businessSectors);
}