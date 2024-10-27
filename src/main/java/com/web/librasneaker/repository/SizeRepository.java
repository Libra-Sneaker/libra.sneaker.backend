package com.web.librasneaker.repository;

import com.web.librasneaker.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity,String> {
    Optional<SizeEntity> getSizeEntityByName(String name);
}
