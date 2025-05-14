package com.project.repository;

import com.project.enities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<CandidateEntity,Long> {

}
