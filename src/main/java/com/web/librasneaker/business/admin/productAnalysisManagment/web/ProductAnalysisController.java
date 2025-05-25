package com.web.librasneaker.business.admin.productAnalysisManagment.web;

import com.web.librasneaker.business.admin.productAnalysisManagment.service.ProductAnalysisService;
import com.web.librasneaker.dto.analystManagement.AnalystManagementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/analysis")
public class ProductAnalysisController {

    private final ProductAnalysisService productAnalysisService;

    @GetMapping("/best-sale")
    public ResponseEntity<Page<AnalystManagementDTO>> getBestSaleProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productAnalysisService.getBestSaleProduct(pageable));
    }
}