package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.service.JobPostingData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobPostingEntityRepositoryTest {

    private JobPostingRepository jobPostingRepository;

    @Qualifier("jobPostingEntityRepositoryImpl")
    @Autowired
    private JobPostingEntityRepository jobPostingEntityRepository;

    @Autowired
    public JobPostingEntityRepositoryTest(JobPostingRepository jobPostingRepository, EntityManager entityManager) {
        this.jobPostingRepository = jobPostingRepository;
        this.jobPostingEntityRepository = new JobPostingEntityRepositoryImpl(entityManager);
    }

    @DisplayName("채용공고와 등록한 회사를 같이 조회한다.")
    @Test
    void findAllJobPostingData(){
        // given
        List<JobPosting> jobPostingDatas = jobPostingEntityRepository.findAllJobPostingData();
        // when

        // then

    }


}