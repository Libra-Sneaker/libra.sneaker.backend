package com.web.librasneaker.repository;

import com.web.librasneaker.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity,String> {
    Optional<MaterialEntity> findMaterialEntityByName(String name);

}
