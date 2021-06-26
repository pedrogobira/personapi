package com.pedrogobira.personapi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Record(s) not found");
    }
}
