package com.web.librasneaker.business.admin.customerManagement.service;

import com.web.librasneaker.dto.customerManagement.CreateCustomerDTO;
import com.web.librasneaker.dto.customerManagement.FindCustomerDTO;
import com.web.librasneaker.dto.customerManagement.ListCustomerDTO;
import com.web.librasneaker.dto.customerManagement.UpdateCustomerDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import org.springframework.data.domain.Page;

public interface CustomerManagementService {
    String createCustomer (CreateCustomerDTO request);

    String updateCustomer (UpdateCustomerDTO request);

    Page<ListCustomerDTO> searchCustomer (FindCustomerDTO req);

    String updateStatusCustomer (String id, Integer deleteFlag);
}
