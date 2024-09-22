package com.web.librasneaker.repository;

import com.web.librasneaker.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    CustomerEntity findByEmailAndDeleteFlagFalse(String email);


}
