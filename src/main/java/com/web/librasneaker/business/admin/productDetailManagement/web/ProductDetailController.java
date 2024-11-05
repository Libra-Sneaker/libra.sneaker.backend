package com.web.librasneaker.business.admin.productDetailManagement.web;

import com.web.librasneaker.business.admin.productDetailManagement.service.ProductDetailService;
import com.web.librasneaker.dto.productDetailDTO.CreateProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.FindProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailListDTO;
import com.web.librasneaker.dto.productDetailDTO.UpdateProductDetailManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.UpdateProductManagementDTO;
import com.web.librasneaker.entity.ProductDetailEntity;
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

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/productDetail")
public class ProductDetailController {
    public final ProductDetailService productDetailService;

    @PostMapping("/create")
    public ResponseEntity<String> createProductDetail (@RequestBody CreateProductDetailDTO request) {
        return ResponseEntity.ok().body(productDetailService.createProductDetail(request));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDetailListDTO>> searchProduct(FindProductDetailDTO request) {
        Page<ProductDetailListDTO> response = productDetailService.getAllProductDetails(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDetailEntity>> getAllProductDetail( ) {
        List<ProductDetailEntity> response = productDetailService.getProductDetail();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProductDetail(@RequestBody UpdateProductDetailManagementDTO request) {
        return ResponseEntity.ok().body(productDetailService.updateProductDetail(request));
    }

    @DeleteMapping("delete/{id}")
    public String deleteProductDetail(@PathVariable String id) {
        return productDetailService.deleteProductDetail(id);
    }


}
