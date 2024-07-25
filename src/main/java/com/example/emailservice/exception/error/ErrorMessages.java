package com.example.emailservice.exception.error;

public class ErrorMessages {
    public static final String EMAIL_SEND_FAILED = "Failed to send verification email";
    public static final String INVALID_CODE = "Invalid verification code";
    public static final String CODE_EXPIRED = "Verification code expired";
    public static final String INVALID_LENGTH = "Length must be greater than 0";
    public static final String CODE_ALREADY_REQUESTED = "Verification code already requested within valid time frame";
}
