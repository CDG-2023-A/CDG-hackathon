package com.cdg.hackathon.jobposting.service;

import com.cdg.hackathon.applyhistory.domain.ApplyHistory;
import com.cdg.hackathon.applyhistory.repository.ApplyHistoryRepository;
import com.cdg.hackathon.company.domain.Company;
import com.cdg.hackathon.company.repository.CompanyRepository;
import com.cdg.hackathon.jobposting.domain.JobPosting;
import com.cdg.hackathon.jobposting.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.jobposting.dto.request.DoApplyRequest;
import com.cdg.hackathon.jobposting.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.jobposting.dto.response.GetDetailJobPostingResponse;
import com.cdg.hackathon.jobposting.dto.response.GetJobPostingResponse;
import com.cdg.hackathon.jobposting.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public GetDetailJobPostingResponse getJobPosting(Long postId) {
        JobPosting findJobPosting = jobPostingRepository.findById(postId).orElseThrow(RuntimeException::new);

        Company findcompany = companyRepository.findById(findJobPosting.getCompanyId()).orElseThrow(RuntimeException::new);

        List<Long> otherJopPostingId = findOtherJobPostings(findJobPosting);

        return new GetDetailJobPostingResponse(findcompany, findJobPosting, otherJopPostingId);

    }

    public List<GetJobPostingResponse> getJobPostings(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        if (null != keyword && !keyword.isEmpty()) {
            return jobPostingRepository.findJobPostingsDtoByKeyWord(keyword,pageable);
        } else {
            return jobPostingRepository.findJobPostingsDto(pageable);
        }

    }


    @Transactional(readOnly = false)
    public void updateJobPosting(Long postId, UpdateJobPostingRequest request) {
        JobPosting findJobPosting = jobPostingRepository.findById(postId).orElseThrow(RuntimeException::new);

        findJobPosting.update(request);
    }

    @Transactional(readOnly = false)
    public void deleteJobPosting(Long postId) {
        JobPosting findJobPosting = jobPostingRepository.findById(postId).orElseThrow(RuntimeException::new);

        jobPostingRepository.delete(findJobPosting);
    }


    @Transactional(readOnly = false)
    public void doApply(DoApplyRequest request) {
        JobPosting findJobPosting = jobPostingRepository.findById(request.getJobPostingId()).orElseThrow(RuntimeException::new);

        Optional<ApplyHistory> existingApplication = applyHistoryRepository.findByJobPostingIdAndUserId(request.getJobPostingId(), request.getUserId());

        if (existingApplication.isPresent()) {
            throw new IllegalStateException("사용자는 1회만 지원 가능합니다.");
        }

        ApplyHistory applyHistory = new ApplyHistory(request);

        applyHistoryRepository.save(applyHistory);
    }

    private List<Long> findOtherJobPostings(JobPosting findJobPosting) {
        List<JobPosting> allJobPostingsWithSameCompanyId = jobPostingRepository.findAllByCompanyId(findJobPosting.getCompanyId());

        List<Long> otherJobPostingIds = allJobPostingsWithSameCompanyId.stream()
                .filter(jobPosting -> !jobPosting.equals(findJobPosting))
                .map(JobPosting::getId)
                .collect(Collectors.toList());

        return otherJobPostingIds;
    }
}
