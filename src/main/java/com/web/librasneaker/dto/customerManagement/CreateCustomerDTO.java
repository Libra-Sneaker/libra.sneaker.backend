package com.web.librasneaker.dto.customerManagement;

import com.web.librasneaker.config.constant.enumconstant.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateCustomerDTO {
    @NotBlank(message = "Không được để trống")
    private String name;

    @NotNull(message = "Không được để trống")
    private Date dateOfBirth;

    @NotBlank(message = "Không được để trống")
    private String address;

    @NotNull(message = "Không được để trống")
    private String phoneNumber;

    @NotBlank(message = "Không được để trống")
    private String email;

    @NotNull(message = "Không được để trống")
    private Integer sex;

}
