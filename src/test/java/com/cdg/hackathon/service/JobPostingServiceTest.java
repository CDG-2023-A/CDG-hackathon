package com.cdg.hackathon.service;

import com.cdg.hackathon.jobposting.domain.JobPosting;
import com.cdg.hackathon.jobposting.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.jobposting.repository.JobPostingRepository;
import com.cdg.hackathon.jobposting.service.JobPostingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application.yml")
class JobPostingServiceTest {

    @Autowired
    private JobPostingService jobPostingService;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @DisplayName("채용공고를 생성해서 저장한다.")
    @Test
    public void createJobPosting() {
        // given
        Long companyId = 1L;
        String position = "벡엔드 주니어 개발자";
        Integer reward = 1000000;
        String content = "원티드랩에서 벡엔드 주니어 개발자를 채용합니다.";
        String techStack = "springBoot";

        CreateJobPostingRequest request = new CreateJobPostingRequest(companyId,
                position, reward, content, techStack);

        // when
        Long jobPostingId = jobPostingService.createJobPosting(request);
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId).orElseThrow();

        // then
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


    @DisplayName("채용공고 상세정보를 가져온다")
    @Test
    public void getJobPosting() {
        //given
        Long companyId = 1L;
        String position = "벡엔드 주니어 개발자";
        Integer reward = 1000000;
        String content = "원티드랩에서 벡엔드 주니어 개발자를 채용합니다.";
        String techStack = "springBoot";
        CreateJobPostingRequest request = new CreateJobPostingRequest(companyId, position, reward, content, techStack);
        Long jobPostingId = jobPostingService.createJobPosting(request);
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId).orElseThrow();
    }
}