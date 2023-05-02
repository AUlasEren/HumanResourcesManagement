package com.hrm.repository;

import com.hrm.repository.entity.Advance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance, String> {

}
