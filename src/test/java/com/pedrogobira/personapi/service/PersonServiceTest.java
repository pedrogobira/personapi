package com.pedrogobira.personapi.service;

import com.pedrogobira.personapi.dto.PersonDto;
import com.pedrogobira.personapi.dto.ResponseMessageDto;
import com.pedrogobira.personapi.entities.Person;
import com.pedrogobira.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pedrogobira.personapi.utils.PersonUtils.createFakeDto;
import static com.pedrogobira.personapi.utils.PersonUtils.createFakeEntity;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    void testGivenPersonDtoThenReturnSavedMessage() {
        PersonDto dto = createFakeDto();
        Person expectedSavedPerson = createFakeEntity();

        Mockito.when(repository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        ResponseMessageDto expectedMessage = createExpectedMessage(expectedSavedPerson.getCpf());
        ResponseMessageDto responseMessage = service.save(dto);

        Assertions.assertEquals(expectedMessage, responseMessage);
    }

    private ResponseMessageDto createExpectedMessage(String cpf) {
        return ResponseMessageDto
                .builder()
                .message("Person saved with CPF: " + cpf)
                .build();
    }

}
