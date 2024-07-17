package com.example.emailservice.exception;

import com.example.emailservice.exception.error.ErrorCodes;
import com.example.emailservice.exception.error.ErrorMessages;
import com.example.emailservice.exception.error.GrpcException;

public class InvalidLengthException extends GrpcException {
    public InvalidLengthException() {
        super(ErrorCodes.INVALID_LENGTH_CODE, ErrorMessages.INVALID_LENGTH);
    }
}