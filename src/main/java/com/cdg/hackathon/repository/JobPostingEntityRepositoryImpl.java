package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.domain.QJobPosting;
import com.cdg.hackathon.service.JobPostingData;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.cdg.hackathon.domain.QJobPosting.jobPosting;

@Repository
public class JobPostingEntityRepositoryImpl implements JobPostingEntityRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public JobPostingEntityRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<JobPosting> findAllJobPostingData() {
        List<JobPosting> jobPostings = jpaQueryFactory
                .selectFrom(jobPosting)
                .fetch();
        return jobPostings;
    }
}
