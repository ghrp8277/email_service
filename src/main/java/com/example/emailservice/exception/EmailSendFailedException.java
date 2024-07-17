package com.example.emailservice.exception;

import com.example.emailservice.exception.error.ErrorCodes;
import com.example.emailservice.exception.error.ErrorMessages;
import com.example.emailservice.exception.error.GrpcException;

public class EmailSendFailedException extends GrpcException {
    public EmailSendFailedException() {
        super(ErrorCodes.EMAIL_SEND_FAILED_CODE, ErrorMessages.EMAIL_SEND_FAILED);
    }
}