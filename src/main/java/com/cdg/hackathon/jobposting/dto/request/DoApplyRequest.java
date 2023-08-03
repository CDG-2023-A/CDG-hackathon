package com.cdg.hackathon.jobposting.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DoApplyRequest {

    @NotNull
    private Long jobPostingId;

    @NotNull
    private Long userId;

}
