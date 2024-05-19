package org.example.maids.exceptions;


public class ReturnDateException extends RuntimeException {

    public ReturnDateException(String errorMessage){
        super(errorMessage);
    }
}
