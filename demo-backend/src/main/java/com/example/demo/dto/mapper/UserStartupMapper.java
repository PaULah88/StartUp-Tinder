package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserStartupDTO;
import com.example.demo.entity.User;

@Mapper
public interface UserStartupMapper {

    UserStartupMapper INSTANCE = Mappers.getMapper(UserStartupMapper.class);

    UserStartupDTO userToUserDto(User contact);

    List<UserStartupDTO> userToUserDtoList(List<User> users);

    User userDTOtoUser(UserStartupDTO userdto);

}