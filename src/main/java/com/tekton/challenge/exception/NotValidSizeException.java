package com.tekton.challenge.exception;

public class NotValidSizeException extends RuntimeException{

    public NotValidSizeException(Integer id) {
        super(String.format("height must be greater than 2 and less than 105 it is %d", id));
    }
}
