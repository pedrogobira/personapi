package com.pedrogobira.personapi.utils;

import com.pedrogobira.personapi.dto.PhoneDto;
import com.pedrogobira.personapi.entities.Phone;
import com.pedrogobira.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final long ID = 1L;
    private static final PhoneType TYPE = PhoneType.MOBILE;
    private static final String NUMBER = "73912345678";

    public static PhoneDto createFakeDto() {
        return PhoneDto.builder()
                .id(ID)
                .type(TYPE)
                .number(NUMBER)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(ID)
                .type(TYPE)
                .number(NUMBER)
                .build();
    }
}
