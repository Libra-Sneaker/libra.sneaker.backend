package com.web.librasneaker.business.admin.couponManagement.service;

import com.web.librasneaker.dto.couponManagement.CreateCouponDTO;
import com.web.librasneaker.dto.couponManagement.FindCouponDTO;
import com.web.librasneaker.dto.couponManagement.ListCouponDTO;
import com.web.librasneaker.dto.couponManagement.UpdateCouponDTO;
import org.springframework.data.domain.Page;

public interface CouponService {
    String createCoupon (CreateCouponDTO request);

    Page<ListCouponDTO> searchCoupon (FindCouponDTO req);

    String updateCoupon ( UpdateCouponDTO request);
}
