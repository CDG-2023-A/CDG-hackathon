package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.Company;
import com.cdg.hackathon.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    Optional<JobPosting> findById(Long jobPostingId);

    List<JobPosting> findAllByCompanyId(Long companyId);
}
