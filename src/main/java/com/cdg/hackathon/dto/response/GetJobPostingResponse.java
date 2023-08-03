package com.cdg.hackathon.dto.response;

import com.cdg.hackathon.domain.Company;
import com.cdg.hackathon.domain.JobPosting;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetJobPostingResponse {

    private Long jobPostingId;

    private String name;

    private String country;

    private String region;

    private String position;

    private Integer reward;

    private String techStack;

    private String content;

    private List<Long> otherJobPositing;

    @Builder
    private GetJobPostingResponse(Long jobPostingId, String name, String country, String region, String position, Integer reward, String techStack, String content, List<Long> otherJobPositing) {
        this.jobPostingId = jobPostingId;
        this.name = name;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
        this.content = content;
        this.otherJobPositing = otherJobPositing;
    }

    public GetJobPostingResponse(Company company, JobPosting jobPosting, List<Long> otherJobPositing){
        this.jobPostingId = jobPosting.getId();
        this.name = company.getName();
        this.country = company.getCountry();
        this.region = company.getRegion();
        this.position = jobPosting.getPosition();
        this.reward = jobPosting.getReward();
        this.techStack = jobPosting.getTechStack();
        this.content = jobPosting.getContent();
        this.otherJobPositing = otherJobPositing;
    }
}
