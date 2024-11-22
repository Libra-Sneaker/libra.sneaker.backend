package com.web.librasneaker.dto.billDetailManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBillDetailDTO {
    private String billId;
    private Double price;
    private String productDetailId;
    private Integer quantity;
}
