package com.cdg.hackathon.jobposting.domain;

import com.cdg.hackathon.jobposting.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.jobposting.dto.request.UpdateJobPostingRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "JOB_POSTING")
public class JobPosting extends BaseEntity{

    private String position;

    private Integer reward;

    @Column(name = "tech_stack")
    private String techStack;

    private String content;

    @NotNull
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

    public void update(UpdateJobPostingRequest request) {
        if(null != request.getContent()){
            this.content = request.getContent();
        }

        if(null != request.getReward()){
            this.reward = request.getReward();
        }

        if(null != request.getTechStack()){
            this.techStack = request.getTechStack();
        }

        if(null != request.getPosition()){
            this.position = request.getPosition();
        }

    }
}
