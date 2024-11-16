package com.web.librasneaker.dto.employeeManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import com.web.librasneaker.config.constant.enumconstant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEmployeeDTO extends PageableRequest {

    private String code;

    private String name;

    private String phone;

    private String email;

    private Integer sex;

    private Role role;

    private Integer deleteFlag;
}
