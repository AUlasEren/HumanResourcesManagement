package com.hrm.repository;

import com.hrm.repository.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findOptionalByEmail(String email);

}
