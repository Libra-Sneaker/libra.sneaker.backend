package com.web.librasneaker.business.admin.productManagement.web;

import com.web.librasneaker.business.admin.brandManagement.service.BrandManagementService;
import com.web.librasneaker.business.admin.materialManagement.service.MaterialManagementService;
import com.web.librasneaker.business.admin.productManagement.service.ProductManagementService;
import com.web.librasneaker.business.admin.typeManagement.service.TypeManagementService;
import com.web.librasneaker.dto.productManagement.CreateProductManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.UpdateProductManagementDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
    @RequestMapping("/admin/productManagement")
public class ProductManagementController {
    private final BrandManagementService brandManagementService;
    private final ProductManagementService productManagementService;
    private final TypeManagementService typeManagementService;
    private final MaterialManagementService materialManagementService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductManagementDTO request) {
        return ResponseEntity.ok().body(productManagementService.createProduct(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductManagementDTO request) {
        return ResponseEntity.ok().body(productManagementService.updateProduct(request));
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable String id) {


        return productManagementService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductListDTO>> searchProduct(FindProductManagementDTO request) {
        Page<ProductListDTO> response = productManagementService.getProductManagementResponse(request);
        return ResponseEntity.ok(response);
    }


}
