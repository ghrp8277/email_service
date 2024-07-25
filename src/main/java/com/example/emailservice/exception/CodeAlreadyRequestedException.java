package com.example.emailservice.exception;

import com.example.emailservice.exception.error.ErrorCodes;
import com.example.emailservice.exception.error.ErrorMessages;
import com.example.emailservice.exception.error.GrpcException;

public class CodeAlreadyRequestedException extends GrpcException {
    public CodeAlreadyRequestedException() {
        super(ErrorCodes.EMAIL_CODE_ALREADY_REQUESTED_CODE, ErrorMessages.CODE_ALREADY_REQUESTED);
    }
}
