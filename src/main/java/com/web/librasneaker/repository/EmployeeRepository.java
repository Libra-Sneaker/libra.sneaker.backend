package com.web.librasneaker.repository;

import com.web.librasneaker.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    EmployeeEntity findByEmailAndDeleteFlagFalse(String email);

}
