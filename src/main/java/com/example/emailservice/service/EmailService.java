package com.example.emailservice.service;

import com.example.emailservice.entity.EmailCount;
import com.example.emailservice.exception.*;
import com.example.emailservice.repository.EmailCountRepository;
import org.springframework.mail.SimpleMailMessage;
import com.example.emailservice.constants.RedisConstants;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.redis.core.RedisTemplate;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private EmailCountRepository emailCountRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, RedisTemplate<String, Object> redisTemplate) {
        this.mailSender = mailSender;
        this.redisTemplate = redisTemplate;
    }

    public static String generateRandomCode(int length) {
        if (length <= 0) {
            throw new InvalidLengthException();
        }
        Random random = new Random();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public String emailCodeInRedisWithTTL(String email) {
        String code = generateRandomCode(RedisConstants.VERIFICATION_CODE_LENGTH);
        String key = RedisConstants.VERIFICATION_CODE_KEY_PREFIX + email;
        String storedCode = (String) redisTemplate.opsForValue().get(key);

        if (storedCode == null){
            redisTemplate.opsForValue().set(key, code, RedisConstants.DEFAULT_EXPIRATION_SECONDS, TimeUnit.SECONDS);

            try {
                sendVerificationEmail(email, code);
                incrementEmailCount(email);
            } catch (MessagingException e) {
                throw new EmailSendFailedException();
            }

            return "Success";
        }

        throw new CodeAlreadyRequestedException();
    }

    public boolean verifyEmailCode(String email, String code) {
        String key = RedisConstants.VERIFICATION_CODE_KEY_PREFIX + email;
        String storedCode = (String) redisTemplate.opsForValue().get(key);

        if (storedCode == null) {
            throw new CodeExpiredException();
        }

        if (!code.equals(storedCode)) {
            throw new InvalidCodeException();
        }

        return true;
    }

    public void sendVerificationEmail(String email, String code) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Email Verification Code");
		message.setText("Your verification code is: " + code);

        mailSender.send(message);
    }

    public void incrementEmailCount(String email) {
        Optional<EmailCount> optionalEmailCount = emailCountRepository.findByEmail(email);
        EmailCount emailCount;

        if (optionalEmailCount.isPresent()) {
            emailCount = optionalEmailCount.get();
            emailCount.setCount(emailCount.getCount() + 1);
            emailCount.setUpdateDate(LocalDateTime.now());
        } else {
            emailCount = new EmailCount();
            emailCount.setEmail(email);
            emailCount.setCount(1);
            emailCount.setDate(LocalDateTime.now());
            emailCount.setUpdateDate(LocalDateTime.now());
        }

        emailCountRepository.save(emailCount);
    }
}
