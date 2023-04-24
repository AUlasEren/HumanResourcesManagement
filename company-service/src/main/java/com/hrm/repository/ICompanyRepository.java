package com.hrm.repository;

import com.hrm.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends JpaRepository<Company,Long> {


    Optional<Company> findOptionalByCompanyName(String companyName);
}
