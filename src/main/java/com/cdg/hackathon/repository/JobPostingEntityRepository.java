package com.cdg.hackathon.repository;

import com.cdg.hackathon.service.JobPostingData;

import java.util.List;

public interface JobPostingEntityRepository {

    List<JobPostingData> findAllJobPostingData(String keyword);
}
