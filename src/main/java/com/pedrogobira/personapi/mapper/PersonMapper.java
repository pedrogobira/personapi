package com.pedrogobira.personapi.mapper;

import com.pedrogobira.personapi.dto.PersonDto;
import com.pedrogobira.personapi.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDto toDto(Person person);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toEntity(PersonDto dto);
}
