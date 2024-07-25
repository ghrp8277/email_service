package com.example.emailservice.exception.error;

import io.grpc.Status;

public class ErrorCodes {
    public static final Status.Code EMAIL_SEND_FAILED_CODE = Status.Code.INTERNAL;
    public static final Status.Code INVALID_CODE_CODE = Status.Code.INVALID_ARGUMENT;
    public static final Status.Code CODE_EXPIRED_CODE = Status.Code.NOT_FOUND;
    public static final Status.Code INVALID_LENGTH_CODE = Status.Code.INVALID_ARGUMENT;
    public static final Status.Code EMAIL_CODE_ALREADY_REQUESTED_CODE = Status.Code.ALREADY_EXISTS;
}