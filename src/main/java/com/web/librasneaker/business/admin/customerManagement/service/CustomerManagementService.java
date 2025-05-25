package com.web.librasneaker.business.admin.customerManagement.service;

import com.web.librasneaker.dto.customerManagement.CreateCustomerDTO;
import com.web.librasneaker.dto.customerManagement.CustomerNameResponse;
import com.web.librasneaker.dto.customerManagement.CustomerStatisticsResponse;
import com.web.librasneaker.dto.customerManagement.FindByNameDTO;
import com.web.librasneaker.dto.customerManagement.FindCustomerDTO;
import com.web.librasneaker.dto.customerManagement.ListCustomerDTO;
import com.web.librasneaker.dto.customerManagement.UpdateCustomerDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import com.web.librasneaker.entity.CustomerEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerManagementService {
    String createCustomer (CreateCustomerDTO request);

    String updateCustomer (UpdateCustomerDTO request);

    Page<ListCustomerDTO> searchCustomer (FindCustomerDTO req);

    String updateStatusCustomer (String id, Integer deleteFlag);

    List<CustomerNameResponse> searchByName (FindByNameDTO name);

    CustomerStatisticsResponse getCustomerStatistics();
}
