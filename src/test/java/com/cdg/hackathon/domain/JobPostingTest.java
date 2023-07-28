package com.cdg.hackathon.domain;

import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class JobPostingTest {

    @DisplayName("CreateJobPostingRequest를 입력받아서 JobPosting을 생성한다.")
    @Test
    public void fromCreateJobRequest() {
        // given
        Long companyId = 1L;
        String position = "벡엔드 주니어 개발자";
        Integer reward = 1000000;
        String content = "원티드랩에서 벡엔드 주니어 개발자를 채용합니다.";
        String techStack = "springBoot";

        CreateJobPostingRequest request = new CreateJobPostingRequest(companyId,
                position, reward, content, techStack);

        // when
        JobPosting jobPosting = JobPosting.fromCreateJobRequest(request);

        // then
        // jobPosting이 잘 생성되었는지 검증해야함.
        // jobPosting 객체를 비교한다?
        // jobPosting 내부의 컬럼값이 잘 들어가있는지 확인
        Assertions.assertThat(jobPosting).extracting(
                        "companyId",
                        "position",
                        "reward",
                        "content",
                        "techStack")
                .containsExactlyInAnyOrder(
                        companyId,
                        position,
                        reward,
                        content,
                        techStack
                );
    }

    @DisplayName("UpdateJobPostingRequest를 입력받아서 JobPosting을 수정한다.")
    @Test
    public void updateJobPosting() {
        //given
        JobPosting jobPosting = JobPosting.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다.")
                .techStack("springBoot")
                .build();

        //when
        String position = "벡엔드 시니어 개발자";
        Integer reward = 10000000;
        String content = "원티드랩에서 벡엔드 시니어 개발자를 채용합니다.";
        String techStack = "springBoot";
        UpdateJobPostingRequest request = new UpdateJobPostingRequest(position, reward, content, techStack);
        jobPosting.update(request);
        //then

        Assertions.assertThat(jobPosting).extracting(
                        "position",
                        "reward",
                        "content",
                        "techStack")
                .containsExactlyInAnyOrder(
                        position,
                        reward,
                        content,
                        techStack
                );
    }

}