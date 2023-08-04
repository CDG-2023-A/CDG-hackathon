package com.cdg.hackathon.dto.request;

import com.cdg.hackathon.service.request.CreateJobPostingServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter // 객체를 생성하고 내부 값을 꺼내야하는데 getter가 없으면 그거를 못해서 에러가 생겨요
@NoArgsConstructor // 기본 생성자 얘가 있어야지 오브젝트 매퍼가 인식
@AllArgsConstructor
@Builder
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

    public CreateJobPostingServiceRequest toServiceData() {
        return CreateJobPostingServiceRequest.builder()
                .companyId(companyId)
                .position(position)
                .reward(reward)
                .content(content)
                .techStack(techStack)
                .build();
    }


}
