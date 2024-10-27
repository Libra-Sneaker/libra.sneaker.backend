package com.web.librasneaker.repository;

import com.web.librasneaker.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,String> {
    Optional<ColorEntity> findColorEntitiesByName(String name);
}
