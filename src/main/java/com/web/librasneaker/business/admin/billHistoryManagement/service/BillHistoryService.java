package com.web.librasneaker.business.admin.billHistoryManagement.service;

import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;

import java.util.List;
import java.util.Optional;

public interface BillHistoryService {
    List<ListBillHistoryDTO> getBillHistory (String id);
}
