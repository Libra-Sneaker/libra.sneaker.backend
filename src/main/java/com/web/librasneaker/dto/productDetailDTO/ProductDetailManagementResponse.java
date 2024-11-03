package com.web.librasneaker.dto.productDetailDTO;

public interface ProductDetailManagementResponse {

    Long getRowNum();

    String getProductId();

    String getProductName();

    String getBrandName();

    String getTypeName();

    String getMaterialName();

    String getColorName();

    String getSizeName();

    String getDescription();

    Double getPrice();

    Integer getStatus();

    Long getCreatedDate();

    Long getQuantity();
}
