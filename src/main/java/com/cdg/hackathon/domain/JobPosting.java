package com.cdg.hackathon.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting extends BaseEntity{

    private String position;

    private Integer reward;

    private String techStack;

    private String content;

    private Long companyId;


    @Builder
    private JobPosting(String position, Integer reward, String techStack, String content, Long companyId) {
        this.position = position;
        this.reward = reward;
        this.techStack = techStack;
        this.content = content;
        this.companyId = companyId;
    }
}
