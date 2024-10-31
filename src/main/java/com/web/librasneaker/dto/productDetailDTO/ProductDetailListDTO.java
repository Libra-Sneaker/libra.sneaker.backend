package com.web.librasneaker.dto.productDetailDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailListDTO {

    private Long rowNum;

    private String id;

    private String productName;

    private String description;

    private String sizeName;

    private String brandName;

    private String typeName;

    private String materialName;

    private String colorName;

    private String price;

    private Long createdDate;

    private String status;

    private Long quantity;

}