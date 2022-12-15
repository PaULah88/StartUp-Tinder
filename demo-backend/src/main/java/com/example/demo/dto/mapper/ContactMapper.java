package com.example.demo.dto.mapper;

import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDto(Contact contact);

    List<ContactDTO> contactToContactDtoList(List<Contact> contacts);

    Contact contactDTOtoContact(ContactDTO contactdto);

}