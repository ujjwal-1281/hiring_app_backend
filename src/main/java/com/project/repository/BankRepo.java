package com.project.repository;

import com.project.enities.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankEntity,Long> {
}
