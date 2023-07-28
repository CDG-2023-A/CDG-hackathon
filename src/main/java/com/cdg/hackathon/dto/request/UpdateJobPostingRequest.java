package com.cdg.hackathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateJobPostingRequest {

    private String position;

    private Integer reward;

    private String content;

    private String tech_stack;

}
