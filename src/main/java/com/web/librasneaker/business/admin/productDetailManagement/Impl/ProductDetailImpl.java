package com.web.librasneaker.business.admin.productDetailManagement.Impl;

import com.web.librasneaker.business.admin.productDetailManagement.service.ProductDetailService;
import com.web.librasneaker.dto.productDetailDTO.CreateProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.FindProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailListDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailManagementResponse;
import com.web.librasneaker.dto.productDetailDTO.UpdateProductDetailManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.entity.ProductDetailEntity;
import com.web.librasneaker.entity.ProductEntity;
import com.web.librasneaker.repository.ProductDetailRepository;
import com.web.librasneaker.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    private ModelMapper modelMapper;

    private final ProductDetailRepository productDetailRepository;

    private final ProductRepository productRepository;


    @Override
    public Page<ProductDetailListDTO> getAllProductDetails(FindProductDetailDTO req) {

        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        Page<ProductDetailManagementResponse> pageResponse = productDetailRepository.getProductDetailResponse(pageable, req);

        // Map ProductManagementResponse to ProductListDTO
        Page<ProductDetailListDTO> pageDTO = pageResponse.map(productDetailManagementResponse ->
                modelMapper.map(productDetailManagementResponse, ProductDetailListDTO.class)
        );

        return pageDTO;
    }

    @Override
    public String createProductDetail(CreateProductDetailDTO createProductDetail) {
//        Optional<ProductEntity> existingProduct = productRepository.findById(CreateProductDetailDTO);
        Optional<ProductEntity> existingProduct = productRepository.findById(createProductDetail.getProductId());
        if (!existingProduct.isPresent()) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }

        // Create product detail
        ProductDetailEntity productDetail = new ProductDetailEntity();
        productDetail.setProductId(existingProduct.get().getId());
        productDetail.setName(createProductDetail.getProductDetailName());
        productDetail.setProductCode(createProductDetail.getProductCode());
        productDetail.setPrice(createProductDetail.getPrice());
        productDetail.setQuantity(createProductDetail.getQuantity());
        productDetail.setDescription(createProductDetail.getDescription());
        productDetail.setColorId(createProductDetail.getColorId());
        productDetail.setSizeId(createProductDetail.getSizeId());
        productDetail.setStatus(createProductDetail.getStatus());
        productDetailRepository.save(productDetail);

        return "Thêm chi tiết sản phẩm thành công!";
    }

    @Override
    public String updateProductDetail(UpdateProductDetailManagementDTO request) {
        // Step 1: Verify if the main product exists
        Optional<ProductEntity> existingProduct = productRepository.findById(request.getProductId());
        if (!existingProduct.isPresent()) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }

        // Step 2: Verify if the product detail associated with this product exists
        Optional<ProductDetailEntity> existingProductDetail = productDetailRepository.findById(request.getId());
        if (!existingProductDetail.isPresent() ||
                !existingProductDetail.get().getProductId().equals(request.getProductId())) {
            throw new IllegalArgumentException("Chi tiết sản phẩm không tồn tại cho sản phẩm này");
        }

        // Step 3: Update the product detail fields
        ProductDetailEntity productDetail = existingProductDetail.get();
        productDetail.setName(request.getProductDetailName());
        productDetail.setProductCode(request.getProductCode());
        productDetail.setQuantity(request.getQuantity());
        productDetail.setDescription(request.getDescription());
        productDetail.setPrice(request.getPrice());
        productDetail.setSizeId(request.getSizeId());
        productDetail.setColorId(request.getColorId());
        productDetail.setStatus(request.getStatus());

        // Optionally update main product fields if needed
//        ProductEntity product = existingProduct.get();
//        product.setBrandId(request.getBrandId());
//        product.setMaterialId(request.getMaterialId());
//        product.setTypeId(request.getTypeId());

        // Save the updated entities
        productDetailRepository.save(productDetail);
//        productRepository.save(product);

        return "Cập nhật chi tiết sản phẩm thành công!";
    }


    @Override
    public String deleteProductDetail(String id) {
        Optional<ProductDetailEntity> existingProductDetail = productDetailRepository.findById(id);
        if (!existingProductDetail.isPresent()) {
            throw new IllegalArgumentException("Chi tiết sản phẩm không tồn tại");
        }

        // Delete the specific product detail
        productDetailRepository.deleteById(id);
        return "Xóa chi tiết sản phẩm thành công!";
    }
}
