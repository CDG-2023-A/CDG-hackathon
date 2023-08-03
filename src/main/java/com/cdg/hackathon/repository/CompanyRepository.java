package com.cdg.hackathon.repository;

import com.cdg.hackathon.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
