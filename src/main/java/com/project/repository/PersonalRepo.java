package com.project.repository;

import com.project.enities.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepo extends JpaRepository<PersonalEntity,Long> {
}
