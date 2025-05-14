package com.project.enities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="personal_details")
public class PersonalEntity {

    @OneToOne
    @JoinColumn(name = "candidate_id",  nullable = false)
    private CandidateEntity candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //RELATED BASIC INFORMATION
    private LocalDate dob;
    private String gender;
    private String nationality;

    //ADDRESS
    private String address;

}
