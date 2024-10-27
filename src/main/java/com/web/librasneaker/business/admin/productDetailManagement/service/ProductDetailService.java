package com.web.librasneaker.business.admin.productDetailManagement.service;

import com.web.librasneaker.dto.productDetailDTO.CreateProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.FindProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailListDTO;
import com.web.librasneaker.dto.productDetailDTO.UpdateProductDetailManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import org.springframework.data.domain.Page;

public interface ProductDetailService {

    // get all
    Page<ProductDetailListDTO> getAllProductDetails (FindProductDetailDTO req);

    // create a new ProductDetailService
    String createProductDetail (CreateProductDetailDTO createProductDetail);

    // update a ProductDetailService
    String updateProductDetail(UpdateProductDetailManagementDTO request);

    String deleteProductDetail(String id);
}
