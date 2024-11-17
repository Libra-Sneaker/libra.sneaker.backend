package com.web.librasneaker.dto.billManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListBillDTO {

    private String id;

    private Integer rowNum;

    private String code;

    private String type;

    private Double totalAmount;

    private Date datePayment;

    private String address;

    private Integer status;

    private String customerName;

    private String employeeName;

    private Long createdDate;

    private String phoneNumber;

    private String recipient;
}
