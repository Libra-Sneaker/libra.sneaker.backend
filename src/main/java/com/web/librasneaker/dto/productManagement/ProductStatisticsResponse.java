package com.web.librasneaker.dto.productManagement;

public interface ProductStatisticsResponse {
    String getProductId();
    String getProductName();
    Long getTotalSoldQuantity();
    String getUrlImg();
}
