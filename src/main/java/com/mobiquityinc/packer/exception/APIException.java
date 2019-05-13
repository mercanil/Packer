package com.mobiquityinc.packer.exception;

/**
 * Thrown when consumer sends incorrect data to API.
 *
 * <ul>
 * <li>Invalid Input resource
 * <li>Invalid Input parameters
 * <li>Invalid File path
 * <li>Null File path
 * </ul>
 */
public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }
}
