package com.project.services;

import com.project.enities.CandidateEntity;
import com.project.repository.CandidateRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final CandidateRepo candidateRepo;


    public void sendJobOffer(Long candidateId){
        CandidateEntity candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(candidate.getEmail());
            helper.setSubject("Job Offer");
            helper.setText("<h1> Congratulations."+
                    candidate.getFirstName()+"!</h1>" +
                    "<p>We are pleased to offer you a position.</p>", true);
            mailSender.send(message);
        }catch(MessagingException m){
            throw new RuntimeException("Failed to send Mail "+m);
        }

    }

    public void sendOtp(String email, String otp){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("OTP");
            helper.setText("<h1> OTP Verification</h1>" +
                    "<p>Your OTP is " + otp + "</p>", true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Failed to send Mail "+ e.getMessage());
        }
    }

}
