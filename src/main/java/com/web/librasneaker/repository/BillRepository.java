package com.web.librasneaker.repository;

import com.web.librasneaker.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity,String> {
    Optional<BillEntity> getBillEntityByCode(String code);
}
