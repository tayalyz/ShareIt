package ru.company.shareit.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return this.handleExceptionInternal(ex, null, headers, status, request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, Object> errors = new HashMap<>();
//        List<Map<String, String>> errorDetails = new ArrayList<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            Map<String, String> fieldError = new HashMap<>();
//            fieldError.put("field", error.getField());
//            fieldError.put("defaultMessage", error.getDefaultMessage());
//            errorDetails.add(fieldError);
//        });
//
//        errors.put("status", ex.getStatusCode());
//        errors.put("errors", errorDetails);
//        return errors;
//    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFoundExceptions(NotFoundException ex) {
        return getErrorMsg(ex);
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicateDataExceptions(DuplicateDataException ex) {
        return getErrorMsg(ex);
    }

    @ExceptionHandler(UpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUpdateExceptions(UpdateException ex) {
        return getErrorMsg(ex);
    }

    @ExceptionHandler(ItemIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleItemIsNotAvailableExceptions(ItemIsNotAvailableException ex) {
        return getErrorMsg(ex);
    }

    @ExceptionHandler(CommentIsNotAllowedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleCommentIsNotAllowedExceptions(CommentIsNotAllowedException ex) {
        return getErrorMsg(ex);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleBookingRejectedExceptions(ForbiddenException ex) {
        return getErrorMsg(ex);
    }

    private static Map<String, Object> getErrorMsg(RuntimeException ex) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("message", ex.getMessage());
        return errors;
    }
}
