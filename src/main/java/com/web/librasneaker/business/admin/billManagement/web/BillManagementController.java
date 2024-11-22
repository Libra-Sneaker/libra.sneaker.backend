package com.web.librasneaker.business.admin.billManagement.web;

import com.web.librasneaker.business.admin.billManagement.service.BillManagementService;
import com.web.librasneaker.dto.billManagement.FindBillDTO;
import com.web.librasneaker.dto.billManagement.ListBillDTO;
import com.web.librasneaker.dto.brandManagement.UpdateDeleteFlagRequestDTO;
import com.web.librasneaker.dto.colorManagement.CreateColorDTO;
import com.web.librasneaker.dto.employeeManagement.FindEmployeeDTO;
import com.web.librasneaker.dto.employeeManagement.ListEmployeeDTO;
import com.web.librasneaker.entity.BillEntity;
import com.web.librasneaker.entity.ColorEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/billManagement")
public class BillManagementController {
    private final BillManagementService billManagementService;

    @PostMapping("/create")
    public ResponseEntity<String> createBill (BillEntity bill) {
        return ResponseEntity.ok().body(billManagementService.createBill(bill));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListBillDTO>> searchBill(FindBillDTO request) {
        request.setStatus(1);
        Page<ListBillDTO> response = billManagementService.getBillResponse(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/infoBill/{id}")
    public ResponseEntity<ListBillDTO> getBillDetails(@PathVariable String id) {
        Optional<ListBillDTO> bill = billManagementService.getInfoBill(id);
        return bill.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật phương thức này để không nhận giá trị từ tham số trong URL
    @GetMapping("/getBillWithStatus")
    public ResponseEntity<List<BillEntity>> getBillWithStatus() {
        Integer status = 0; // Cố định status = 0
        Integer deleteFlag = 0;
        List<BillEntity> bills = billManagementService.getBillWithStatus(status,deleteFlag);
        return ResponseEntity.ok().body(bills);
    }

    @GetMapping("/getBillAvailable")
    public ResponseEntity<List<BillEntity>> getBillWithAvailable() {
        Integer status = 1; // Cố định status = 0
        Integer deleteFlag = 0;
        List<BillEntity> bills = billManagementService.getBillWithStatus(status,deleteFlag);
        return ResponseEntity.ok().body(bills);
    }

    @PutMapping("/updateDeleteFlag/{id}")
    public ResponseEntity<String> updateDeleteFlag(@PathVariable String id) {
        String result = billManagementService.updateDeleteFlag(id);
        return ResponseEntity.ok(result);
    }
}
