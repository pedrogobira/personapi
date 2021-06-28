package com.pedrogobira.personapi.utils;

import com.pedrogobira.personapi.dto.PersonDto;
import com.pedrogobira.personapi.entities.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "Pedro";
    private static final String LAST_NAME = "Gobira";
    private static final String CPF = "369.333.878-79";
    private static final long ID = 1L;
    private static final LocalDate BIRTH_DATE = LocalDate.of(1999, 12, 10);

    public static PersonDto createFakeDto(){
        return PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .id(ID)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeDto()))
                .build();
    }

    public static Person createFakeEntity(){
        return Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .id(ID)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}
