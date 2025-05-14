package com.project.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bank_details")
public class BankEntity {
    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //BANK DETAILS
    private String bankName;
    private String accountNumber;
    private String ifscCode;


}

