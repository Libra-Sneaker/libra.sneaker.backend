package com.web.librasneaker.dto.productDetailDTO;

import com.web.librasneaker.business.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindProductDetailDTO extends PageableRequest {
    private String name;

    private Integer status;

    private String brandId;

    private String materialId;

    private String typeId;

    private String colorId;

    private String sizeId;

    private String price;


}
