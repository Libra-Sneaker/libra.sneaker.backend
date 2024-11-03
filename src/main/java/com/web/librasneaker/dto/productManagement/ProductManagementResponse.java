package com.web.librasneaker.dto.productManagement;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface ProductManagementResponse {

    Long getRowNum();

    String getProductId();

    String getProductName();

    CriteriaBuilder.In getStatus();

    Long getCreatedDate();

    Long getTotalQuantity();

}
