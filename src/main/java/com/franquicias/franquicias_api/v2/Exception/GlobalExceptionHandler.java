package com.franquicias.franquicias_api.v2.Exception;

import com.franquicias.franquicias_api.v2.Exception.BranchExceptions.BranchNotFoundException;
import com.franquicias.franquicias_api.v2.Exception.BranchExceptions.FranchiseListEmptyException;
import com.franquicias.franquicias_api.v2.Exception.FranchiseExceptions.FranchiseNotFoundException;
import com.franquicias.franquicias_api.v2.Exception.ProductExceptions.ProductNotBelongToBranch;
import com.franquicias.franquicias_api.v2.Exception.ProductExceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateNameException(DuplicateNameException ex) {
        return buildErrorResponse("DUPLICATE_NAME", ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FranchiseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFranchiseNotFoundException(FranchiseNotFoundException ex) {
        return buildErrorResponse("FRANCHISE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBranchNotFoundException(BranchNotFoundException ex) {
        return buildErrorResponse("BRANCH_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        return buildErrorResponse("PRODUCT_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FranchiseListEmptyException.class)
    public ResponseEntity<ErrorResponse> handleFranchiseListEmptyException(FranchiseListEmptyException ex) {
        return buildErrorResponse("FRANCHISE_LIST_EMPTY", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotBelongToBranch.class)
    public ResponseEntity<ErrorResponse> handleProductNotBelongToBranchException(ProductNotBelongToBranch ex){
        return buildErrorResponse("PRODUCT_NOT_BELONG_TO_BRANCH", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String errorCode, String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(errorCode, message, status);
        return new ResponseEntity<>(errorResponse, status);
    }
}
