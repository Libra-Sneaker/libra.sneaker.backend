package com.web.librasneaker.dto.customerManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import com.web.librasneaker.config.constant.enumconstant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindCustomerDTO extends PageableRequest {
    private String searchTerm;

    private Integer sex;

    private Integer deleteFlag;

}
