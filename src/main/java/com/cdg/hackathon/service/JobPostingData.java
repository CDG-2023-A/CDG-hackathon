package com.cdg.hackathon.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter // setter없으면 projection시 데이터가 null로 들어감
public class JobPostingData {

    private Long jobPostingId;

    private String name;

    private String region;

    private String position;

    private Integer reward;

    private String techStack;
}
