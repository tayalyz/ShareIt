package ru.company.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UpdateException extends RuntimeException {

    String message;

    public UpdateException(String msg) {
        super(msg);
        message = msg;
    }

    public HttpStatusCode getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
