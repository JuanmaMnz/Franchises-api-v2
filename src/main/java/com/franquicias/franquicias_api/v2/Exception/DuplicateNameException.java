package com.franquicias.franquicias_api.v2.Exception;

public class DuplicateNameException extends RuntimeException{

    public DuplicateNameException(String message) {
        super(message);
    }
}
