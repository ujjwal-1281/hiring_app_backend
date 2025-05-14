package com.project.repository;

import com.project.enities.JobOfferNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOfferNotification,Long> {
}
