package com.hrm.repository;

import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Vocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVocationRepository extends MongoRepository<Vocation, String> {
}
