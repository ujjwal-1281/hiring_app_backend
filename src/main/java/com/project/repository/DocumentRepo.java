package com.project.repository;

import com.project.enities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepo extends JpaRepository<DocumentEntity,Long> {
}
