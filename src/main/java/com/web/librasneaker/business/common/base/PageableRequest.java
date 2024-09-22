package com.web.librasneaker.business.common.base;

import com.web.librasneaker.config.constant.classconstant.PaginationConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageableRequest {

    private int page = PaginationConstant.DEFAULT_PAGE;
    private int size = PaginationConstant.DEFAULT_SIZE;
}

