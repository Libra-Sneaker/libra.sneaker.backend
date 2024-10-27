package com.web.librasneaker.dto.productManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductManagementDTO {
    @NotBlank(message = "Không được để trống")
    private String name;

    @NotBlank(message = "Không được để trống")
    private String description;

    @NotBlank(message = "Không được để trống")
    private String status;

    @NotNull(message = "Không được để trống")
    private String brandId;

    @NotNull (message = "Không được để trống")
    private String materialId;

    @NotNull (message = "Không được để trống")
    private String typeId;

}
