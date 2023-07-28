package com.cdg.hackathon.service;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.DoApplyRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    // 1. jobPosting 생성 (service 메서드에서 new를 해버리면 단위테스트가 안돼)
    // 2. jobPosting 디비에 저장
    // 각각의 책임을 와부로 분리해서 단위테스트 하기 용이하게
    @Transactional(readOnly = false)
    public Long createJobPosting(CreateJobPostingRequest request) {
        JobPosting jobPosting = JobPosting.fromCreateJobRequest(request);
        JobPosting save = jobPostingRepository.save(jobPosting);
        return save.getId();
    }

//    public Page<JobPostingData> getJobPostings(String keyword) {
//    }

    public void updateJobPosting(Long postId, UpdateJobPostingRequest request) {

        JobPosting jobPosting = jobPostingRepository.findById(postId).orElseThrow(RuntimeException::new);

        jobPosting.update(request);
    }

    public void deleteJobPosting(Long postId) {

        JobPosting jobPosting = jobPostingRepository.findById(postId).orElseThrow(RuntimeException::new);

        jobPostingRepository.delete(jobPosting);

    }

//    public GetJobPostingResponse getJobPosting(Long postId) {
//    }

    public void doApply(DoApplyRequest request) {
    }
}
