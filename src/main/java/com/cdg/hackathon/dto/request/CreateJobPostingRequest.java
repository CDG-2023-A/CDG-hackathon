package com.cdg.hackathon.dto.request;

import com.cdg.hackathon.service.JobPostingData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostingRequest {

    @Positive(message = "companyId는 양수가 되어야 합니다.")
    private Long companyId;

    @NotBlank(message = "position은 필수 값 입니다.")
    private String position;

    @Positive(message = "reward는 양수가 되어야 합니다.")
    private Integer reward;

    @NotBlank(message = "content는 필수 값 입니다.")
    private String content;

    @NotBlank(message = "techStack은 필수 값 입니다.")
    private String techStack;

    public JobPostingData toServiceData() {
        return JobPostingData.builder()
                .companyId(companyId)
                .position(position)
                .reward(reward)
                .content(content)
                .techStack(techStack)
                .build();
    }


}
