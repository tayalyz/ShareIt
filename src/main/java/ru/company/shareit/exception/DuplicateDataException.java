package ru.company.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class DuplicateDataException extends RuntimeException {

    String message;

    public DuplicateDataException(String msg) {
        super(msg);
        message = msg;
    }

    public HttpStatusCode getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
