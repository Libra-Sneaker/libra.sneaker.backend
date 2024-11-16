package com.web.librasneaker.dto.employeeManagement;

import com.web.librasneaker.config.constant.enumconstant.Role;

import java.util.Date;

public interface EmployeeResponse {
    Long getRowNum();

    String getAvatar();

    String getEmployeeId();

    String getEmployeeCode();

    String getEmployeeName();

    Date getDateOfBirth();

    String getEmail();

    String getPhoneNumber();

    String getAddress();

    String getSex();

    Integer getRole();

    String getpassword();

    Integer getDeleteFlag();

}
