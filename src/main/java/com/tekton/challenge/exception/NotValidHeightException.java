package com.tekton.challenge.exception;

public class NotValidHeightException extends RuntimeException {
    public NotValidHeightException(Integer id) {
        super(String.format("height must be greater than 0 and less than 104, it is %d", id));
    }
}
