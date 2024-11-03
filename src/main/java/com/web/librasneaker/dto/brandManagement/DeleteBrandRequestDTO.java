package com.web.librasneaker.dto.brandManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBrandRequestDTO {
    private String id;
    private String name;
    private Integer status;
}
