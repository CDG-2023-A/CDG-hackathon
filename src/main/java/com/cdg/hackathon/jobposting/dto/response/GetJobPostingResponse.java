package com.cdg.hackathon.jobposting.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public GetJobPostingResponse(Long jobPostingId, String name, String country, String region, String position, Integer reward, String content, String techStack) {
        this.jobPostingId = jobPostingId;
        this.name = name;
        this.country = country;
        this.region = region;
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
        this.content = content;
    }
}
