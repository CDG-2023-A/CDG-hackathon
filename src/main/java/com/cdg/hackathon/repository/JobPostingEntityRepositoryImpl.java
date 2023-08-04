package com.cdg.hackathon.repository;

import com.cdg.hackathon.service.JobPostingData;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cdg.hackathon.domain.QCompany.company;
import static com.cdg.hackathon.domain.QJobPosting.jobPosting;

@Repository
public class JobPostingEntityRepositoryImpl implements JobPostingEntityRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public JobPostingEntityRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<JobPostingData> findAllJobPostingData(String keyword) {
        String searchKeyword = (keyword == null || keyword.isEmpty()) ? "%" : "%" + keyword + "%";

        return jpaQueryFactory
                .select(Projections.bean(
                        JobPostingData.class,
                        jobPosting.id,
                        company.name,
                        company.region,
                        jobPosting.position,
                        jobPosting.reward,
                        jobPosting.techStack
                ))
                .from(jobPosting)
                .innerJoin(company).on(jobPosting.companyId.eq(company.id))
                .where(
                        jobPosting.position.like(searchKeyword)
                                .or(company.name.like(searchKeyword))
                )
                .fetch();
    }
}
