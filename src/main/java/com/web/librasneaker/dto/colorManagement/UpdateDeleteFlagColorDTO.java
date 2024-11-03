package com.web.librasneaker.dto.colorManagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDeleteFlagColorDTO {
    @NotBlank(message = "Không được để trống")
    private String id;

    @NotNull(message = "Không được để trống")
    private Integer deleteFlag;
}
