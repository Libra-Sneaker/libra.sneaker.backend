package com.web.librasneaker.business.admin.billManagement.web;

import com.web.librasneaker.business.admin.billManagement.service.BillManagementService;
import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/billManagement")
public class BillManagementController {
    private final BillManagementService billManagementService;

    @GetMapping("/search")
    public ResponseEntity<Page<ListBillDTO>> searchBill(FindBillDTO request) {
        Page<ListBillDTO> response = billManagementService.getBillResponse(request);
        return ResponseEntity.ok(response);
    }
}
