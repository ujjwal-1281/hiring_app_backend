package com.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    @NotNull(message = "Candidate ID is required")
    private Long candidateId;
    @NotNull(message = "Document type is required")
    private String documentType;
}
