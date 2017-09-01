package com.twilllogistics.assessment.twilluserapi.exception;

/**
 * Created by turgay on 31/08/17.
 *
 * Exception class which is used for any invalid data entry through REST service.
 */
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message) {
        super(message);
    }
}
