package com.web.librasneaker.dto.couponManagement;

import com.web.librasneaker.business.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FindCouponDTO extends PageableRequest {

    private String couponId;

    private String searchTerm;      // name và code

    private String startDate;

    private String endDate;

    private Integer type;       // 0: personal, 1: public

    private Integer unit;       // 0: %. 1:vnd

    private Integer status;     // 0: ko hoạt đông, 1: đang diễn ra, 2: hết hạn.
}
