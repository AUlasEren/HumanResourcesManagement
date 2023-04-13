package com.hrm.repository;

import com.hrm.repository.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
    Optional<Auth> findOptionalByUsername(String username);
    List<Auth> findAllByRole(ERole roles);
}
