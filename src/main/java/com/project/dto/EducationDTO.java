package com.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {


    @NotBlank
    private String highestQualification;

    @NotBlank
    private String university;

    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Enter a valid year")
    private String passingYear;

}
