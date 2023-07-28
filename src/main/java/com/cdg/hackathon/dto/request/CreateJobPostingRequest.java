package com.cdg.hackathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostingRequest {

    @NotBlank
    private Long companyId;

    private String position;

    private Integer reward;

    private String content;

    private String techStack;

}
