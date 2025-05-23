package com.web.librasneaker.repository;

import com.web.librasneaker.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,String> {



    Optional<BrandEntity> findBrandEntityByName(String name);
}
