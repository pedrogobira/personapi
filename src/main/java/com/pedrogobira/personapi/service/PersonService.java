package com.pedrogobira.personapi.service;

import com.pedrogobira.personapi.dto.PersonDto;
import com.pedrogobira.personapi.dto.ResponseMessageDto;
import com.pedrogobira.personapi.entities.Person;
import com.pedrogobira.personapi.exception.BusinessException;
import com.pedrogobira.personapi.exception.NotFoundException;
import com.pedrogobira.personapi.mapper.PersonMapper;
import com.pedrogobira.personapi.repository.PersonRepository;
import com.pedrogobira.personapi.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonMapper mapper = PersonMapper.INSTANCE;
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseMessageDto save(PersonDto dto) {
        Optional<Person> optionalPerson = repository.findByCpf(dto.getCpf());
        if (optionalPerson.isPresent()) throw new BusinessException(MessageUtils.PERSON_ALREADY_EXISTS);
        Person person = mapper.toEntity(dto);
        repository.save(person);
        return createResponseMessage("Person saved with CPF: ", person.getCpf());
    }

    @Transactional(readOnly = true)
    public List<PersonDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDto findById(Long id) {
        return mapper.toDto(verifyIfExists(id));
    }

    @Transactional
    public void delete(Long id) {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    @Transactional
    public ResponseMessageDto update(Long id, PersonDto dto){
        verifyIfExists(id);
        Person person = mapper.toEntity(dto);
        repository.save(person);
        return createResponseMessage("Person updated. CPF: ", person.getCpf());
    }

    private Person verifyIfExists(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private ResponseMessageDto createResponseMessage(String message, String cpf) {
        return ResponseMessageDto.builder().message(message + cpf).build();
    }
}
