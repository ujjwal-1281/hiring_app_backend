package com.project.listener;

import com.project.config.RabbitMqConfig;
import com.project.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobNotificationListener {
    private final EmailService emailService;

    @RabbitListener(queues = RabbitMqConfig.JOB_OFFER_QUEUE)
    public void handleJobOffer(Long candidateId) {
        emailService.sendJobOffer(candidateId);
    }
}
