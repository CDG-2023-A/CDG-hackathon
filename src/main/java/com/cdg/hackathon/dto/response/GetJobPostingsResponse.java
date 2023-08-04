package com.cdg.hackathon.dto.response;

import com.cdg.hackathon.service.JobPostingData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetJobPostingsResponse {

    private List<JobPostingData> createJobPostingData;

    public GetJobPostingsResponse(List<JobPostingData> createJobPostingData) {
        this.createJobPostingData = createJobPostingData;
    }
}
