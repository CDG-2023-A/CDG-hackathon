package com.cdg.hackathon.jobposting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostingRequest {

    @NotNull
    private Long companyId;

    private String position;

    private Integer reward;

    private String content;

    private String techStack;

}
