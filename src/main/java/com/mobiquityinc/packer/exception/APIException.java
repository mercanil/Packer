package com.mobiquityinc.packer.exception;

public class APIException extends RuntimeException {

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Exception cause) {
        super(message, cause);
    }
}
