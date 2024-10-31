package com.web.librasneaker.dto.productDetailDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindProductDetailDTO {
    private String name;

    private String description;

    private String status;

    private String brandId;

    private String materialId;

    private String typeId;

    private String colorId;

    private String sizeId;

    private String price;

    // biet nguoi dung dang o trang nao
    private Integer page;

    // biet nguoi dung muon xem bao nhieu phan tu tren 1 trang
    private Integer size;
}
