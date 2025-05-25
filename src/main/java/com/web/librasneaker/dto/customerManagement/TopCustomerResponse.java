package com.web.librasneaker.dto.customerManagement;

public interface TopCustomerResponse {
    String getCustomerId();
    String getCustomerName();
    String getCustomerCode();
    String getPhoneNumber();
    String getEmail();
    Long getTotalOrders();
    Double getTotalSpent();
}
