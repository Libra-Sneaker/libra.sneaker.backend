package com.web.librasneaker.repository;

import com.web.librasneaker.entity.BillHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillHistoryRepository extends JpaRepository<BillHistoryEntity,String> {
}
