package com.web.librasneaker.dto.billManagement;

import java.util.Date;

public interface BillResponse {

    String getBillCode();

    String getType();

    Double getTotalAmount();

    String getDatePayment();

    String getAddress();

    Integer getStatus();

    String getEmployeeName();

    String getCustomerName();
}
