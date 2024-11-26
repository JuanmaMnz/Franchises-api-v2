package com.franquicias.franquicias_api.v2.Exception.ProductExceptions;

public class ProductNotBelongToBranch extends RuntimeException {
    public ProductNotBelongToBranch(String message) {

        super(message);
    }
}
