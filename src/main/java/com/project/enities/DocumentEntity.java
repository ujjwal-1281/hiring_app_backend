package com.project.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

    @ManyToOne
    @JoinColumn(name="candidate_id",nullable=false)
    private CandidateEntity candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String documentType;
    private String fileUrl;
    private Boolean verified;
}
