package com.web.librasneaker.repository;

import com.web.librasneaker.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity,String> {
    Optional<TypeEntity> getTypeEntityByName(String name);
}
