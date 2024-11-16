package com.web.librasneaker.dto.billManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListBillDTO {

    @NotBlank(message = "Không được để trống")
    private String code;

    @NotBlank(message = "Không được để trống")
    private String type;

    @NotNull(message = "Không được để trống")
    private Double totalAmount;

    @NotNull(message = "Không được để trống")
    private Date datePayment;

    @NotBlank(message = "Không được để trống")
    private String address;

    @NotNull(message = "Không được để trống")
    private Integer status;

    @NotNull(message = "Không được để trống")
    private String customerName;

    @NotNull(message = "Không được để trống")
    private String employeeName;

}
