package com.web.librasneaker.dto.productManagement;

public interface ProductManagementResponse {

    Long getRowNum();

    String getProductId();

    String getProductName();

    String getStatus();

    Long getCreatedDate();

    Long getTotalQuantity();

}
