package com.example.emailservice.exception;

import com.example.emailservice.exception.error.ErrorCodes;
import com.example.emailservice.exception.error.ErrorMessages;
import com.example.emailservice.exception.error.GrpcException;

public class CodeExpiredException extends GrpcException {
    public CodeExpiredException() {
        super(ErrorCodes.CODE_EXPIRED_CODE, ErrorMessages.CODE_EXPIRED);
    }
}