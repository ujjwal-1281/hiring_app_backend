package com.project.security;

import com.project.config.RabbitMqConfig;
import com.project.security.otp.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Handles user registration, login, and authentication
@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final OtpService otpService;
    private final RabbitTemplate rabbitTemplate;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       OtpService otpService,
                       RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.otpService = otpService;
        this.rabbitTemplate = rabbitTemplate;
    }

    // Register a new user
    public UserEntity register(UserDTO userDTO) {
        // Check if username exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Create and save user with hashed password
        UserEntity user = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return userRepository.save(user);
    }

    // Authenticate user and generate JWT
    public String login(UserDTO userDTO) {
        try{
            // Verify credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
            // Generate OTP for authenticated user
            String otp = otpService.generateOtp(userDTO.getUsername());
            rabbitTemplate.convertAndSend(RabbitMqConfig.OTP_QUEUE,
                    userDTO.getUsername() + ":" + otp);

            return "OTP sent to " + userDTO.getUsername();
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public String verify(String username, String otp){
        if (otpService.validateOtp(username, otp)) {
            UserDetails userDetails = loadUserByUsername(username);
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    public String forgotPassword(String username) {
        String otp = otpService.generateOtp(username);
        rabbitTemplate.convertAndSend(RabbitMqConfig.OTP_QUEUE,
                username + ":" + otp);

        return "OTP sent to " + username;
    }

    public String resetPassword(String username, String otp, String newPassword) {
        if (otpService.validateOtp(username, otp)) {
            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return "Password reset successful";
        }
        return "Password reset failed";
    }


    // Load user details for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}