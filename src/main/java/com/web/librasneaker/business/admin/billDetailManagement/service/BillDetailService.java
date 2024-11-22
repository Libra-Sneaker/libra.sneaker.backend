package com.web.librasneaker.business.admin.billDetailManagement.service;

import com.web.librasneaker.dto.billDetailManagement.CreateBillDetailDTO;
import com.web.librasneaker.dto.billDetailManagement.ListBillDetailDTO;
import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;

import java.util.List;

public interface BillDetailService {
    List<ListBillDetailDTO> getBillDetails (String id, Integer deleteFlag);

    String createBillDetail (CreateBillDetailDTO request);

    String updateDeleteFlag (String id);

}
