package com.cdg.hackathon.service;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.dto.request.DoApplyRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.repository.JobPostingRepository;
import com.cdg.hackathon.service.request.CreateJobPostingServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)// 읽기전용 트랜잭션열어줌 // CQRS -> COMMAND(CUD)(2) - 디비에 변화 , QUERY(R) - 조회(8) (디비에 변화x) 분리해야한다. CRUD
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    // 1. jobPosting 생성 (service 메서드에서 new를 해버리면 단위테스트가 안돼)
    // 2. jobPosting 디비에 저장
    // 각각의 책임을 와부로 분리해서 단위테스트 하기 용이하게
    @Transactional(readOnly = false) // 수정, 삭제 등등 가능
    public Long createJobPosting(CreateJobPostingServiceRequest request) {
        JobPosting jobPosting = request.toJobPosting();
        JobPosting save = jobPostingRepository.save(jobPosting);
        return save.getId();
    }

    // 단점 : 귀찮죠 -> 1개의 api당 2개의 request 귀찮다.
    // 장점 : 계층간의 의존성이 구분된다?
    // 장점 : 서비스를 재활용할 때 좋습니다.


    // 1. 채용 공고와 회사를 join해서 가져온다.
    public List<JobPostingData> getJobPostings(String keyword) {
         return jobPostingRepository.findAllJobPostingData(keyword);
    }

    @Transactional
    public void updateJobPosting(Long postId, UpdateJobPostingRequest request) {
        Optional<JobPosting> byId = jobPostingRepository.findById(postId);
        JobPosting jobPosting = byId.get();

        if (!jobPosting.getPosition().equals(request.getPosition())) {

        }


    }

    @Transactional
    public void deleteJobPosting(Long postId) {
    }

//    public GetJobPostingResponse getJobPosting(Long postId) {
//    }

    @Transactional
    public void doApply(DoApplyRequest request) {
    }
}
