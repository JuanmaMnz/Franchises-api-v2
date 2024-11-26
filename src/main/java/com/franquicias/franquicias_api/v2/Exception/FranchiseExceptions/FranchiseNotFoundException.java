package com.franquicias.franquicias_api.v2.Exception.FranchiseExceptions;

public class FranchiseNotFoundException extends RuntimeException{

    public FranchiseNotFoundException(String message) {
        super(message);
    }
}
