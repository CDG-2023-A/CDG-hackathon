package com.cdg.hackathon.jobposting.repository;

import com.cdg.hackathon.jobposting.domain.JobPosting;
import com.cdg.hackathon.jobposting.dto.response.GetJobPostingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    Optional<JobPosting> findById(Long jobPostingId);

    List<JobPosting> findAllByCompanyId(Long companyId);

    @Query("SELECT new com.cdg.hackathon.jobposting.dto.response.GetJobPostingResponse" +
            "(jp.id, c.name, c.country, c.region, jp.position, jp.reward, jp.techStack, jp.content)" +
            "FROM JobPosting jp, Company c WHERE jp.companyId = c.id")
    List<GetJobPostingResponse> findJobPostingsDto(Pageable pageable);


    @Query("SELECT new com.cdg.hackathon.jobposting.dto.response.GetJobPostingResponse" +
            "(jp.id, c.name, c.country, c.region, jp.position, jp.reward, jp.techStack, jp.content)" +
            "FROM JobPosting jp, Company c " +
            "WHERE jp.companyId = c.id " +
            "AND LOWER(CONCAT(c.name, ' ', c.country, ' ', c.region, ' ', jp.position, ' ', jp.content, ' ', jp.techStack)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<GetJobPostingResponse> findJobPostingsDtoByKeyWord(@Param("keyword") String keyword, Pageable pageable);
}
