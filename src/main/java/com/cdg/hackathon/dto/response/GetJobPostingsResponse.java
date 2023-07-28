package com.cdg.hackathon.dto.response;

import com.cdg.hackathon.dto.JobPostingData;
import org.springframework.data.domain.Page;

public class GetJobPostingsResponse {
    public GetJobPostingsResponse(Page<JobPostingData> jobPostingData) {
    }
}
