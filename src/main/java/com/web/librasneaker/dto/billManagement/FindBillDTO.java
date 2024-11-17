package com.web.librasneaker.dto.billManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindBillDTO extends PageableRequest {

    private String searchTerm;

    private String datePaymentStart;

    private Integer status;

    private String datePaymentEnd;


}
