package com.web.librasneaker.dto.transactionManagement;

public interface TransactionResponse {
    String getId();
    String getBillId();
    String getPaymentId();
    Integer getStatus();
    Double getMoney();
    String getCreatedDate();
    Integer getTypeMethod();
    String getEmployeeName();
    Integer getTypeTransaction();
}