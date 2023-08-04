package com.cdg.hackathon.repository;

import com.cdg.hackathon.config.JpaAuditionConfig;
import com.cdg.hackathon.domain.Company;
import com.cdg.hackathon.domain.JobPosting;
import com.cdg.hackathon.service.JobPostingData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest
@Import(JpaAuditionConfig.class)
//@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
class JobPostingEntityRepositoryTest {

    private JobPostingRepository jobPostingRepository;
    private CompanyRepository companyRepository;

    @Qualifier("jobPostingEntityRepositoryImpl")
    @Autowired
    private JobPostingEntityRepository jobPostingEntityRepository;

    @Autowired
    public JobPostingEntityRepositoryTest(JobPostingRepository jobPostingRepository,CompanyRepository companyRepository,EntityManager entityManager) {
        this.jobPostingRepository = jobPostingRepository;
        this.companyRepository = companyRepository;
        this.jobPostingEntityRepository = new JobPostingEntityRepositoryImpl(entityManager);
    }

    @DisplayName("모든 채용공고와 등록한 회사를 같이 조회한다.")
    @Test
    void findAllJobPostingData(){
        // when
        List<JobPostingData> jobPostingDatas = jobPostingEntityRepository.findAllJobPostingData("");

        // then
        assertThat(jobPostingDatas).hasSize(3)
                .extracting("name", "region", "position", "reward", "techStack")
                .containsExactlyInAnyOrder(
                        tuple("원티드랩", "강남", "프론트엔드 시니어 개발자", 2000000, "React"),
                        tuple("원티드랩", "강남", "백엔드 주니어 개발자", 1500000, "SpringBoot"),
                        tuple("인프런", "판교", "백엔드 시니어 개발자", 2000000, "SpringBoot")
                );

    }

    @DisplayName("모든 채용공고와 등록한 회사를 같이 조회할 때 키워드로 포지션을 검색한다.")
    @Test
    void findAllJobPostingDataWithPostitionKeyword(){
        // when
        List<JobPostingData> jobPostingDatas = jobPostingEntityRepository.findAllJobPostingData("백엔드");

        // then
        assertThat(jobPostingDatas).hasSize(2)
                .extracting("name", "region", "position", "reward", "techStack")
                .containsExactlyInAnyOrder(
                        tuple("원티드랩", "강남", "백엔드 주니어 개발자", 1500000, "SpringBoot"),
                        tuple("인프런", "판교", "백엔드 시니어 개발자", 2000000, "SpringBoot")
                );

    }

    @DisplayName("모든 채용공고와 등록한 회사를 같이 조회할 때 키워드로 회사 이름을 검색한다.")
    @Test
    void findAllJobPostingDataWithCompanyNameKeyword(){
        // when
        List<JobPostingData> jobPostingDatas = jobPostingEntityRepository.findAllJobPostingData("인프런");

        // then
        assertThat(jobPostingDatas).hasSize(1)
                .extracting("name", "region", "position", "reward", "techStack")
                .containsExactlyInAnyOrder(
                        tuple("인프런", "판교", "백엔드 시니어 개발자", 2000000, "SpringBoot")
                );

    }


    private JobPosting createJobPosting(String position, Integer reward, String techStack, String content, Long companyId) {
        return JobPosting.builder()
                .position(position)
                .reward(reward)
                .techStack(techStack)
                .content(content)
                .companyId(companyId)
                .build();
    }


    private void prepareTest() {
        Company company1 = new Company("원티드랩", "한국", "강남");
        Company company2 = new Company("인프런", "한국", "판교");
        Company company3 = new Company("토스", "한국", "강남");

        companyRepository.saveAll(List.of(company1, company2, company3));
        List<Company> companies = companyRepository.findAll();

        JobPosting jobPosting1 = createJobPosting(
                "백엔드 주니어 개발자",
                1500000,
                "SpringBoot",
                "원티드랩에서 백엔드 주니어 개발자를 적극 채용합니다. 자격요건은..",
                companies.get(0).getId()
        );

        JobPosting jobPosting2 = createJobPosting(
                "백엔드 시니어 개발자",
                2000000,
                "SpringBoot",
                "인프런에서 백엔드 시니어 개발자를 적극 채용합니다. 자격요건은..",
                companies.get(1).getId()
        );

        JobPosting jobPosting3 = createJobPosting(
                "프론트엔드 시니어 개발자",
                2000000,
                "React",
                "원티드랩에서 프론트엔드 시니어 개발자를 적극 채용합니다. 자격요건은..",
                companies.get(0).getId()
        );

        JobPosting jobPosting4 = createJobPosting(
                "백엔드 주니어 개발자",
                1500000,
                "SpringBoot",
                "토스에서 백엔드 주니어 개발자를 적극 채용합니다. 자격요건은..",
                companies.get(3).getId()
        );

        jobPostingRepository.saveAll(List.of(jobPosting1, jobPosting2, jobPosting3, jobPosting4));
    }


}