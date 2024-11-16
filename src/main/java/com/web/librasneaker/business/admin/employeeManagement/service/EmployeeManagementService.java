package com.web.librasneaker.business.admin.employeeManagement.service;

import com.web.librasneaker.dto.employeeManagement.CreateEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.UpdateEmployeeDTO;
import org.springframework.data.domain.Page;

public interface EmployeeManagementService {
    String createEmployee (CreateEmployeeDTO request);

    String updateEmployee (UpdateEmployeeDTO request);

    Page<ListEmployeeDTO> searchEmployees (FindEmployeeDTO req);

    Page<ListEmployeeDTO> searchEmployeesAllInOne (FindEmployeeDTO req);

    String updateStatusEmployee (String id, Integer deleteFlag);
}
