package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.service.JobPostingData;

import java.util.List;

public interface JobPostingEntityRepository {

    List<JobPosting> findAllJobPostingData();
}
