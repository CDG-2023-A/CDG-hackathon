package com.cdg.hackathon.company.repository;

import com.cdg.hackathon.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
