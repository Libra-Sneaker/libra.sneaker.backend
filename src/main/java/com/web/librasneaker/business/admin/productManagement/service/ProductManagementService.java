package com.web.librasneaker.business.admin.productManagement.service;

import com.web.librasneaker.dto.productManagement.CreateProductManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.dto.productManagement.UpdateProductManagementDTO;
import org.springframework.data.domain.Page;

public interface ProductManagementService {
    String createProduct(CreateProductManagementDTO request);

    String updateProduct(UpdateProductManagementDTO request);

    String deleteProduct(String id);

    Page<ProductListDTO> getProductManagementResponse (FindProductManagementDTO req);


}
