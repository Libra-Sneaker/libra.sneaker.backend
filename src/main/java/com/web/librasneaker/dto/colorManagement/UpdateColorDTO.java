package com.web.librasneaker.dto.colorManagement;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateColorDTO {
    @NotBlank(message = "Không được để trống")
    private String id;

    @NotBlank(message = "Không được để trống")
    private String name;

    @NotBlank(message = "Không được để trống")
    private String description;

    @NotBlank(message = "Không được để trống")
    private String status;
}
