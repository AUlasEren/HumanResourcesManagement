package com.hrm.repository;

import com.hrm.repository.entity.Vocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVocationRepository extends MongoRepository<Vocation, String> {

    Optional<List<Vocation>> findAllByEmployeeId(String id);

}
