package com.web.librasneaker.dto.productDetailDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailListDTO {

    private Long rowNum;

    private String id;

    private String productName;

    private String brandId;

    private String typeId;

    private String materialId;

    private String colorId;

    private String sizeId;

    private String description;

    private String price;

    private String status;
}
