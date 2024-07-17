package com.example.emailservice.exception;

import com.example.emailservice.exception.error.ErrorCodes;
import com.example.emailservice.exception.error.ErrorMessages;
import com.example.emailservice.exception.error.GrpcException;

public class InvalidCodeException extends GrpcException {
    public InvalidCodeException() {
        super(ErrorCodes.INVALID_CODE_CODE, ErrorMessages.INVALID_CODE);
    }
}
