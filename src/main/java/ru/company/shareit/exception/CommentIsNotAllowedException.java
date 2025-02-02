package ru.company.shareit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CommentIsNotAllowedException extends RuntimeException {

    String message;

    public CommentIsNotAllowedException(String msg) {
        super(msg);
        message = msg;
    }

    public HttpStatusCode getStatusCode() {
        return HttpStatus.BAD_REQUEST;  // todo или 405 сюда?
    }
}
