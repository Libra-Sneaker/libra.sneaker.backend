package com.web.librasneaker.repository;

import com.web.librasneaker.entity.CouponCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponCustomerRepository extends JpaRepository<CouponCustomerEntity, String> {
    List<CouponCustomerEntity> findByCouponId(String couponId);
}
