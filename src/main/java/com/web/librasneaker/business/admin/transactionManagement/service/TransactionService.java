package com.web.librasneaker.business.admin.transactionManagement.service;

import com.web.librasneaker.dto.billHistoryManagement.ListBillHistoryDTO;
import com.web.librasneaker.dto.transactionManagement.ListTransactionDTO;

import java.util.List;

public interface TransactionService {
    List<ListTransactionDTO> getTransaction (String id);
}
