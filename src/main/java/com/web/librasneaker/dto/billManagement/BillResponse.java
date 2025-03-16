package com.web.librasneaker.dto.billManagement;

import java.util.Date;

public interface BillResponse {

    String getId();

    Integer getRowNum();

    String getBillCode();

    String getType();

    Double getTotalAmount();

    Integer getTotalQuantity();

    String getDatePayment();

    String getAddress();

    Integer getStatus();

    String getEmployeeName();

    String getCustomerName();

    Long getCreatedDate();

    String getPhonenumber();

    String getRecipient();
}
