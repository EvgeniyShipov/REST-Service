package ru.shipov.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidArgumentException extends RuntimeException {

    public NotValidArgumentException(String message) {
        super("Argument " + message + " is not valid.");
    }
}
