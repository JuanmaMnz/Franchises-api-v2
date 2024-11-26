package com.franquicias.franquicias_api.v2.Exception.BranchExceptions;

public class FranchiseListEmptyException extends RuntimeException {

  public FranchiseListEmptyException(String message) {
    super(message);
  }
}