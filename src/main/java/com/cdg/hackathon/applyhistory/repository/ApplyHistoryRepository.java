package com.cdg.hackathon.applyhistory.repository;

import com.cdg.hackathon.applyhistory.domain.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
    @Query("SELECT a FROM ApplyHistory a WHERE a.jobPostingId = :jobPostingId AND a.userId = :userId")
    Optional<ApplyHistory> findByJobPostingIdAndUserId(Long jobPostingId, Long userId);

}
