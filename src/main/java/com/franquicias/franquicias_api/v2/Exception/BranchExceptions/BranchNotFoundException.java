package com.franquicias.franquicias_api.v2.Exception.BranchExceptions;

public class BranchNotFoundException extends RuntimeException{

    public BranchNotFoundException(String message) {
        super(message);
    }
}
