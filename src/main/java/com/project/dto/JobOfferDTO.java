package com.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {

        private Long id;
        private Long candidateId;
        private Boolean sent;
        private LocalDateTime sentAt;
        private Integer retryCount;
        private String errorMessage;

}
