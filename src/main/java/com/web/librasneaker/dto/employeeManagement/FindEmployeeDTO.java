package com.web.librasneaker.dto.employeeManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import com.web.librasneaker.config.constant.enumconstant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEmployeeDTO extends PageableRequest {

    private String searchTerm;

    private Integer sex;

    private Integer role;

    private Integer deleteFlag;
}
