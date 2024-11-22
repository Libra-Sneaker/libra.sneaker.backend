package com.web.librasneaker.dto.transactionManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListTransactionDTO {
    private String id;
    private String billId;
    private String paymentId;
    private Integer status;
    private Double money;
    private String createdDate;
    private Integer typeMethod;
    private String employeeName;
    private Integer typeTransaction;
}
