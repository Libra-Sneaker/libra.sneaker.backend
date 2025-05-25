package com.web.librasneaker.business.admin.productAnalysisManagment.impl;

import com.web.librasneaker.business.admin.productAnalysisManagment.service.ProductAnalysisService;
import com.web.librasneaker.dto.analystManagement.AnalystManagementDTO;
import com.web.librasneaker.entity.ProductAnalysisEntity;
import com.web.librasneaker.repository.ProductAnalysisRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class ProductAnalysisServiceImpl implements ProductAnalysisService {

    private final ProductAnalysisRepository productAnalysisRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<AnalystManagementDTO> getBestSaleProduct(Pageable pageable) {
        try {
            Page<ProductAnalysisEntity> entityPage = productAnalysisRepository.getTopSellingProducts(pageable);
            return entityPage.map(entity -> modelMapper.map(entity, AnalystManagementDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm bán chạy: " + e.getMessage());
        }
    }
}