package com.cdg.hackathon.service.request;

import com.cdg.hackathon.domain.JobPosting;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateJobPostingServiceRequest {

    private Long companyId;

    private String position;

    private Integer reward;

    private String content;

    private String techStack;

    public JobPosting toJobPosting() {
        return JobPosting.builder()
                .companyId(companyId)
                .position(position)
                .reward(reward)
                .content(content)
                .techStack(techStack)
                .build();
    }
}
