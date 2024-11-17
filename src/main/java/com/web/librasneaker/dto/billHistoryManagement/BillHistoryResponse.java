package com.web.librasneaker.dto.billHistoryManagement;

public interface BillHistoryResponse {
    String getId();
    String getBillId();
    Integer getStatus();
    String getNote();
    Long getCreatedDate();
}
