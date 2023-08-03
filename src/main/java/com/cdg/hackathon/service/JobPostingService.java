package com.cdg.hackathon.service;

import com.cdg.hackathon.domain.ApplyHistory;
import com.cdg.hackathon.domain.Company;
import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.DoApplyRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.dto.response.GetJobPostingResponse;
import com.cdg.hackathon.repository.ApplyHistoryRepository;
import com.cdg.hackathon.repository.CompanyRepository;
import com.cdg.hackathon.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final ApplyHistoryRepository applyHistoryRepository;

    // 1. jobPosting 생성 (service 메서드에서 new를 해버리면 단위테스트가 안돼)
    // 2. jobPosting 디비에 저장
    // 각각의 책임을 와부로 분리해서 단위테스트 하기 용이하게
    @Transactional(readOnly = false)
    public Long createJobPosting(CreateJobPostingRequest request) {
        JobPosting jobPosting = JobPosting.fromCreateJobRequest(request);
        JobPosting save = jobPostingRepository.save(jobPosting);
        return save.getId();
    }

    public GetJobPostingResponse getJobPosting(Long postId) {
        JobPosting findJobPosting = jobPostingRepository.findById(postId).get();

        Company findcompany = companyRepository.findById(findJobPosting.getCompanyId()).get();

        List<Long> otherJopPostingId = findOtherJopPosting(findJobPosting);

        return new GetJobPostingResponse(findcompany, findJobPosting, otherJopPostingId);

    }


    public void updateJobPosting(Long postId, UpdateJobPostingRequest request) {
        Optional<JobPosting> byId = jobPostingRepository.findById(postId);
        JobPosting jobPosting = byId.get();

        if (!jobPosting.getPosition().equals(request.getPosition())) ;


    }

    public void deleteJobPosting(Long postId) {
    }


    public void doApply(DoApplyRequest request) {
        ApplyHistory applyHistory = new ApplyHistory(request);

        applyHistoryRepository.save(applyHistory);
    }

    private List<Long> findOtherJopPosting(JobPosting findJobPosting) {
        List<JobPosting> allByCompanyId = jobPostingRepository.findAllByCompanyId(findJobPosting.getCompanyId());

        List<Long> otherJopPostingId = new ArrayList<>();

        for (JobPosting jobPosting : allByCompanyId) {
            otherJopPostingId.add(jobPosting.getCompanyId());
        }
        return otherJopPostingId;
    }

}
