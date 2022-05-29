package com.tekton.challenge.exception;

import com.tekton.challenge.response.ResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    public static final String HEIGHT_NOT_ALLOWED = "Height not allowed";
    public static final String DATA_SIZE_IS_NOT_ALLOWED = "Data size is not allowed";
    public static final String ERRORS = "errors";

    @ExceptionHandler(NotValidHeightException.class)
    public ResponseEntity<Object> handleNotValidHeightException( NotValidHeightException ex, WebRequest request) {
        return ResponseHandler.generateResponse(HEIGHT_NOT_ALLOWED, HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @ExceptionHandler(NotValidSizeException.class)
    public ResponseEntity<Object> handleNotValidSizeException( NotValidSizeException ex, WebRequest request) {
        return ResponseHandler.generateResponse(DATA_SIZE_IS_NOT_ALLOWED, HttpStatus.BAD_REQUEST, ex.getMessage());

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseHandler.generateResponse(ERRORS, HttpStatus.BAD_REQUEST, errors);
    }

}
