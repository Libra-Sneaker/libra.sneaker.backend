package com.web.librasneaker.dto.billManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBillDTO extends PageableRequest {

    private String searchTerm;

    private String datePaymentStart;

    private String datePaymentEnd;


}
