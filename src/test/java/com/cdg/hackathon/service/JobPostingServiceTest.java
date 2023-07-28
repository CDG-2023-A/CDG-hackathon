package com.cdg.hackathon.service;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.repository.JobPostingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @DisplayName("채용공고를 수정해서 저장한다.")
    @Test
    public void updateJobPosting() {
        // given
        JobPosting jobPosting = JobPosting.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다.")
                .techStack("springBoot")
                .build();
        JobPosting createdJobPosting = jobPostingRepository.save(jobPosting);

        String position = "벡엔드 시니어 개발자";
        Integer reward = 10000000;
        String content = "원티드랩에서 벡엔드 시니어 개발자를 채용합니다.";
        String techStack = "springBoot";

        //when
        UpdateJobPostingRequest request = new UpdateJobPostingRequest(position, reward, content, techStack);
        jobPostingService.updateJobPosting(jobPosting.getId(), request);
        JobPosting findJobPosting = jobPostingRepository.findById(createdJobPosting.getId()).orElseThrow();

        //then
        Assertions.assertThat(findJobPosting).extracting(
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

    @DisplayName("채용공고를 삭제한다.")
    @Test
    public void deletePostingJob() {
        //given
        JobPosting jobPosting = JobPosting.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다.")
                .techStack("springBoot")
                .build();
        JobPosting createdJobPosting = jobPostingRepository.save(jobPosting);

        //when
        jobPostingService.deleteJobPosting(createdJobPosting.getId());

        //then
        assertThrows(NoSuchElementException.class, () -> jobPostingRepository.findById(createdJobPosting.getId()).get());

    }


}