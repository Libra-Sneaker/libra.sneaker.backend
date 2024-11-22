package com.web.librasneaker.business.admin.productDetailManagement.Impl;

import com.web.librasneaker.business.admin.productDetailManagement.service.ProductDetailService;
import com.web.librasneaker.dto.productDetailDTO.CreateProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.FindProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailListDTO;
import com.web.librasneaker.dto.productDetailDTO.ProductDetailManagementResponse;
import com.web.librasneaker.dto.productDetailDTO.SaveListProductDetailDTO;
import com.web.librasneaker.dto.productDetailDTO.UpdateProductDetailManagementDTO;
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

import java.util.List;
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
    public Page<ProductDetailListDTO> getProductCounterSale(FindProductDetailDTO req) {
        return null;
    }

    // Method to generate auto-incremented productCode
    private String generateProductCode() {
        List<String> codes = productDetailRepository.findLatestProductCode();
        int nextNumber = 1;

        if (!codes.isEmpty()) {
            String latestCode = codes.get(0);

            if (latestCode != null && latestCode.startsWith("SP")) {
                nextNumber = Integer.parseInt(latestCode.substring(2)) + 1;
            }
        }

        return String.format("SP%04d", nextNumber);
    }


    @Override
    public String createProductDetail(CreateProductDetailDTO createProductDetail) {
        Optional<ProductEntity> existingProduct = productRepository.findById(createProductDetail.getProductId());

        if (!existingProduct.isPresent()) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }

        ProductDetailEntity productDetail = new ProductDetailEntity();
        productDetail.setProductId(existingProduct.get().getId());
        productDetail.setPrice(createProductDetail.getPrice());
        productDetail.setQuantity(createProductDetail.getQuantity());
        productDetail.setColorId(createProductDetail.getColorId());
        productDetail.setSizeId(createProductDetail.getSizeId());
        productDetail.setStatus(createProductDetail.getStatus());
        productDetail.setDeleteFlag(0);

        // Generate and assign the product code
        productDetail.setProductCode(generateProductCode());

        // Save the product detail entity
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
        productDetail.setQuantity(request.getQuantity());
        productDetail.setPrice(request.getPrice());
        productDetail.setSizeId(request.getSizeId());
        productDetail.setColorId(request.getColorId());
        productDetail.setStatus(request.getStatus());
        productDetail.setDeleteFlag(request.getDeleteFlag());

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

    @Override
    public List<ProductDetailEntity> getProductDetail() {
        return productDetailRepository.findAll();
    }

    @Override
    public String saveListProductDetail(List<SaveListProductDetailDTO> request) {
        for (SaveListProductDetailDTO productDetailDTO : request) {
            Optional<ProductDetailEntity> existingProductDetail = productDetailRepository.findById(productDetailDTO.getId());
            if (!existingProductDetail.isPresent()) {
                throw new IllegalArgumentException("Chi tiết sản phẩm không tồn tại cho sản phẩm này");
            }

            ProductDetailEntity productDetail = existingProductDetail.get();
            productDetail.setQuantity(productDetailDTO.getQuantity());
            productDetail.setPrice(productDetailDTO.getPrice());

            System.out.println(productDetailDTO.getStatus());

            productDetail.setStatus(productDetailDTO.getStatus());
            productDetail.setUrlImage(productDetailDTO.getUrlImg());



            productDetailRepository.save(productDetail);
        }
        return "Cập nhật chi tiết sản phẩm thành công";
    }

}
