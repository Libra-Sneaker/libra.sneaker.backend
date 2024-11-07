package com.web.librasneaker.dto.productManagement;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface ProductManagementResponse {

    Long getRowNum();

    String getProductId();

    String getProductName();

    Integer getStatus();

    Long getCreatedDate();

    Long getTotalQuantity();

}
