package com.web.librasneaker.business.admin.productDetailManagement.service;

import com.web.librasneaker.dto.productDetailDTO.CreateProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.FindProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailListDTO;
import com.web.librasneaker.dto.productDetailDTO.SaveListProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.UpdateProductDetailManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.entity.ProductDetailEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailService {

    // get all
    Page<ProductDetailListDTO> getAllProductDetails (FindProductDetailDTO req);

    // create a new ProductDetailService
    String createProductDetail (CreateProductDetailDTO createProductDetail);

    // update a ProductDetailService
    String updateProductDetail(UpdateProductDetailManagementDTO request);

    String deleteProductDetail(String id);

    List<ProductDetailEntity> getProductDetail ();

    String saveListProductDetail (List<SaveListProductDetailDTO> request);
}
