package com.project.security.otp;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private final Map<String, Otp> otps = new HashMap<>();

    public String generateOtp(String email) {
        StringBuilder otp = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            otp.append(rand.nextInt(10));
        }

        otps.put(email, new Otp(
                otp.toString(),
                LocalDateTime.now().plusMinutes(5)
        ));

        return otp.toString();
    }

    public boolean validateOtp(String email, String otp) {
        if (otps.containsKey(email)) {
            if (otp.equals(otps.get(email).getOtp())
                    && otps.get(email).getExpires().isAfter(LocalDateTime.now())){
                otps.remove(email);
                return true;
            }
        }
        return false;
    }
}
