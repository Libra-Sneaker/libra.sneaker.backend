package com.web.librasneaker.business.admin.productAnalysisManagment.service;

import com.web.librasneaker.dto.analystManagement.AnalystManagementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductAnalysisService {
    Page<AnalystManagementDTO> getBestSaleProduct(Pageable pageable);
}