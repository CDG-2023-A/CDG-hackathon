package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
}
