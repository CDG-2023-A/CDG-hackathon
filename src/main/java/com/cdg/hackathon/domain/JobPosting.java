package com.cdg.hackathon.domain;

import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting extends BaseEntity{

    private String position;

    private Integer reward;

    @Column(name = "tech_stack")
    private String techStack;

    private String content;

    @Column(name = "company_id")
    private Long companyId;


    @Builder
    private JobPosting(String position, Integer reward, String techStack, String content, Long companyId) {
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
        this.content = content;
        this.companyId = companyId;
    }

    public static JobPosting fromCreateJobRequest(CreateJobPostingRequest request) {
        return JobPosting.builder()
                .position(request.getPosition())
                .reward(request.getReward())
                .techStack(request.getTechStack())
                .content(request.getContent())
                .companyId(request.getCompanyId())
                .build();
    }
}
