package com.mobiquityinc.packer.exception;

/**
 * Thrown when consumer sends incorrect data to API.
 *
 * <ul>
 * <li>Invalid Input resource
 * <li>Invalid Input parameters
 * </ul>
 */
public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }
}
