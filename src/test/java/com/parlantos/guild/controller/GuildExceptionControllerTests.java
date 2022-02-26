package com.parlantos.guild.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuildExceptionControllerTests {

    GuildExceptionController guildExceptionController = new GuildExceptionController();

    @Test
    public void validationExceptionHanlder() {
        ValidationException validationException = new ValidationException("test validation exception");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(validationException.getMessage(), HttpStatus.BAD_REQUEST);
        assertEquals(responseEntity, guildExceptionController.exception(validationException));
    }

    @Test
    public void generalExceptionHandler() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity, guildExceptionController.generalException(exception));
    }
}
