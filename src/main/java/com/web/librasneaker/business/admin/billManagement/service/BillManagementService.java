package com.web.librasneaker.business.admin.billManagement.service;

import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import org.springframework.data.domain.Page;

public interface BillManagementService {

    Page<ListBillDTO> getBillResponse (FindBillDTO req);
}
