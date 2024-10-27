package com.web.librasneaker.repository;

import com.web.librasneaker.entity.CartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity,String> {
}
