package com.cdg.hackathon.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DoApplyRequest {

    @NotBlank
    private Long jobPostingId;

    @NotBlank
    private Long userId;

}
