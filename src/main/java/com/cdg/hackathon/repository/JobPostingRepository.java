package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingEntityRepository {

    Optional<JobPosting> findById(Long jobPostingId);

}
