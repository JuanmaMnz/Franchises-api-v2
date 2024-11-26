package com.franquicias.franquicias_api.v2.Exception.ProductExceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {

        super(message);
    }
}
