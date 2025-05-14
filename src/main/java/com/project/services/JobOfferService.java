package com.project.services;


import com.project.config.RabbitMqConfig;
import com.project.enities.CandidateEntity;
import com.project.enities.JobOfferNotification;
import com.project.exception.CandidateNotFoundException;
import com.project.repository.CandidateRepo;
import com.project.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final CandidateRepo candidateRepo;
    private final RabbitTemplate rabbitTemplate;
    private final JobOfferRepository jobOfferRepository;

    public void notify(Long candidateId, String position) {
        CandidateEntity candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
        JobOfferNotification notification = JobOfferNotification.builder()
                .candidate(candidate)
                .sent(false)
                .retryCount(0)
                .build();
        jobOfferRepository.save(notification);

        rabbitTemplate.convertAndSend(RabbitMqConfig.JOB_OFFER_QUEUE,candidateId);
    }


}
