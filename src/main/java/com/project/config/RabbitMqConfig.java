package com.project.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMqConfig {

    public static final String JOB_OFFER_QUEUE = "job_offer_queue";
    public static final String OTP_QUEUE = "otp_queue";

    @Bean
    public Queue jobOfferQueue(){
        return new Queue(JOB_OFFER_QUEUE);
    }

    @Bean
    public Queue otpQueue() {
        return new Queue(OTP_QUEUE);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setRetryTemplate(new RetryTemplate());
        return template;
    }
}
