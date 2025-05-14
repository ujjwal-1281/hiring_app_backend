package com.project.listener;

import com.project.config.RabbitMqConfig;
import com.project.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OtpListener {
    private final EmailService emailService;

    public OtpListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMqConfig.OTP_QUEUE)
    public void receive(String message) {
        String email = message.split(":")[0];
        String otp = message.split(":")[1];
        emailService.sendOtp(email, otp);
    }
}
