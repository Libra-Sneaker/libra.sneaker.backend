package com.web.librasneaker.business.admin.billDetailManagement.web;

import com.web.librasneaker.business.admin.billDetailManagement.service.BillDetailService;
import com.web.librasneaker.dto.billDetailManagement.CreateBillDetailDTO;
import com.web.librasneaker.dto.billDetailManagement.ListBillDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/billDetailsManagement")
public class BillDetailsController {
    private final BillDetailService billDetailService;

    @GetMapping("/infoBillDetails/{id}")
    public ResponseEntity<List<ListBillDetailDTO>> getBillDetails(@PathVariable("id") String id) {
        Integer deleteFlag = 0;
        List<ListBillDetailDTO> billDetails = billDetailService.getBillDetails(id, deleteFlag);
        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateBillDetailDTO request) {
        return ResponseEntity.ok().body(billDetailService.createBillDetail(request));
    }

    @PutMapping("/updateDeleteFlag/{id}")
    public ResponseEntity<String> updateDeleteFlag(@PathVariable String id) {
        String result = billDetailService.updateDeleteFlag(id);
        return ResponseEntity.ok(result);
    }
}
