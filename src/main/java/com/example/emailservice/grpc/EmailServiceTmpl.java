package com.example.emailservice.grpc;

import com.example.emailservice.service.EmailService;
import com.example.emailservice.util.GrpcResponseHelper;
import com.example.grpc.*;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import io.grpc.stub.StreamObserver;


@GrpcService
public class EmailServiceTmpl extends EmailServiceGrpc.EmailServiceImplBase {
    @Autowired
    private EmailService emailService;

    @Autowired
    private GrpcResponseHelper grpcResponseHelper;

    @Override
    public void emailSend(EmailSendRequest request, StreamObserver<Response> responseObserver) {
        String message = emailService.emailCodeInRedisWithTTL(request.getUserId(), request.getEmail());
        grpcResponseHelper.sendJsonResponse("message", message, responseObserver);
    }

    @Override
    public void verifyEmailCode(VerifyEmailCodeRequest request, StreamObserver<Response> responseObserver) {
        boolean isValid = emailService.verifyEmailCode(request.getUserId(), request.getEmail(), request.getCode());
        grpcResponseHelper.sendJsonResponse("isValid", isValid, responseObserver);
    }
}
