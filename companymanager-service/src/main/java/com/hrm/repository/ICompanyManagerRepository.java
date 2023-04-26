package com.hrm.repository;

import com.hrm.repository.entity.CompanyManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends MongoRepository<CompanyManager, String> {
    Optional<CompanyManager> findOptionalByEmail(String email);
}
