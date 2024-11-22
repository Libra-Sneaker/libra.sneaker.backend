package com.web.librasneaker.business.admin.billManagement.service;

import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.colorManagement.UpdateDeleteFlagColorDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.entity.BillEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BillManagementService {

    String createBill(BillEntity bill);

    Page<ListBillDTO> getBillResponse (FindBillDTO req);

    Optional<ListBillDTO> getInfoBill (String id);

    List<BillEntity> getBillWithStatus (Integer status, Integer deleteFlag);

    List<BillEntity> getBillAvailable (Integer status, Integer deleteFlag);


    String updateDeleteFlag (String id);

}
