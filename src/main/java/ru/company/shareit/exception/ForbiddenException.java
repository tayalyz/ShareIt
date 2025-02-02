package ru.company.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ForbiddenException extends RuntimeException {

    String message;

    public ForbiddenException(String msg) {
        super(msg);
        message = msg;
    }

    public HttpStatusCode getStatusCode() {
        return HttpStatus.FORBIDDEN;
    }
}
