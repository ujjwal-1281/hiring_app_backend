package com.project.dto;

import com.project.enities.CandidateEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {


    @NotBlank(message = "Name is required")
    private String firstName;
    private String lastName;

    @Email(message = "Email format is Invalid")
    @NotBlank(message = "Email is required")
    private String email;


    private String phoneNumber;

    private CandidateEntity.Status status;

    private CandidateEntity.OnboardStatus onboardStatus;

    @NotBlank(message = "Profile id required")
    private String profile;

//    @NotNull(message = "Personal details are required")
//    @Valid
//    private PersonalDTO personalDetails;
//
//    @NotNull(message = "Education details are required")
//    @Valid
//    private EducationDTO educationDetails;
//
//    @NotNull(message = "Bank details are required")
//    @Valid
//    private BankDTO bankDetails;
}
