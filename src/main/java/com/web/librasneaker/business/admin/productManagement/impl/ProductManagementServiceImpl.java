package com.web.librasneaker.business.admin.productManagement.impl;

import com.web.librasneaker.business.admin.productManagement.service.ProductManagementService;
import com.web.librasneaker.dto.productManagement.CreateProductDetailManagementDTO;
import com.web.librasneaker.dto.productManagement.CreateProductManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.dto.productManagement.ProductStatisticsResponse;
import com.web.librasneaker.dto.productManagement.ProductStatsDTO;
import com.web.librasneaker.dto.productManagement.UpdateProductManagementDTO;
import com.web.librasneaker.entity.BrandEntity;
import com.web.librasneaker.entity.MaterialEntity;
import com.web.librasneaker.entity.ProductDetailEntity;
import com.web.librasneaker.entity.ProductEntity;
import com.web.librasneaker.entity.TypeEntity;
import com.web.librasneaker.repository.BillDetailRepository;
import com.web.librasneaker.repository.BrandRepository;
import com.web.librasneaker.repository.ColorRepository;
import com.web.librasneaker.repository.MaterialRepository;
import com.web.librasneaker.repository.ProductDetailRepository;
import com.web.librasneaker.repository.ProductRepository;
import com.web.librasneaker.repository.SizeRepository;
import com.web.librasneaker.repository.TypeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Valid
@AllArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {



    @Autowired
    private ModelMapper modelMapper;

    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final BrandRepository brandRepository;
    private final MaterialRepository materialRepository;
    private final TypeRepository typeRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final BillDetailRepository billDetailRepository;


    @Override
    public String createProduct(CreateProductManagementDTO request) {
        // Check if product name already exists
        Optional<ProductEntity> existingProduct = productRepository.getProductEntitiesByName(request.getName());
        if (existingProduct.isPresent()) {
            throw new RuntimeException("Sản phẩm đã tồn tại");
        }

        // Validate each of the main foreign keys
        if (!brandRepository.existsById(request.getBrandId())) {
            throw new RuntimeException("Thương hiệu không tồn tại: " + request.getBrandId());
        }

        if (!materialRepository.existsById(request.getMaterialId())) {
            throw new RuntimeException("Chất liệu không tồn tại: " + request.getMaterialId());
        }

        if (!typeRepository.existsById(request.getTypeId())) {
            throw new RuntimeException("Loại sản phẩm không tồn tại: " + request.getTypeId());
        }

        // Create and save the main ProductEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setStatus(1);  // Default status is 1 (active)
        productEntity.setBrandId(request.getBrandId());
        productEntity.setMaterialId(request.getMaterialId());
        productEntity.setTypeId(request.getTypeId());
        productEntity.setDeleteFlag(0);

        productRepository.save(productEntity);

        // Initialize a variable to track the total quantity
        long totalQuantity = 0;

        // Save each ProductDetailEntity linked to the ProductEntity
        if (request.getDetails() != null) {
            for (CreateProductDetailManagementDTO detailDTO : request.getDetails()) {
                // Validate colorId
                if (!colorRepository.existsById(detailDTO.getColorId())) {
                    throw new RuntimeException("Mã màu không tồn tại: " + detailDTO.getColorId());
                }

                // Validate sizeId
                if (!sizeRepository.existsById(detailDTO.getSizeId())) {
                    throw new RuntimeException("Kích thước không tồn tại: " + detailDTO.getSizeId());
                }

                // Create a new ProductDetailEntity
                ProductDetailEntity detailEntity = new ProductDetailEntity();
                detailEntity.setSizeId(detailDTO.getSizeId());
                detailEntity.setColorId(detailDTO.getColorId());
                detailEntity.setPrice(detailDTO.getPrice());
                detailEntity.setQuantity(detailDTO.getQuantity());
                detailEntity.setUrlImage(detailDTO.getUrlImg());
                detailEntity.setStatus(1);
                detailEntity.setDeleteFlag(0);

                // Link the detail to the main product by setting the productId
                detailEntity.setProductId(productEntity.getId());

                // Generate and set the productCode
                detailEntity.setProductCode(generateProductCode());

                // Save the detail entity in the repository
                productDetailRepository.save(detailEntity);

                // Add quantity to the total
                totalQuantity += detailDTO.getQuantity();
            }
        }

        // If total quantity of product details is 0, set product status to 0 (inactive)
        if (totalQuantity == 0) {
            productEntity.setStatus(0);  // Set the product status to inactive
            productRepository.save(productEntity);  // Update the product status
        }

        return "Thêm sản phẩm và các chi tiết sản phẩm thành công!";
    }

    // Helper method to generate a unique product code
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
    public String updateProduct(UpdateProductManagementDTO request) {
        Optional<ProductEntity> existingProduct = productRepository.findById(request.getId());
        if (!existingProduct.isPresent()){
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        ProductEntity productEntity = existingProduct.get();
        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setStatus(request.getStatus());
        productEntity.setBrandId(request.getBrandId());
        productEntity.setMaterialId(request.getMaterialId());
        productEntity.setTypeId(request.getTypeId());
        productEntity.setDeleteFlag(request.getDeleteFlag());

        productRepository.save(productEntity);

        // Recalculate total quantity
        List<ProductDetailEntity> productDetails = productDetailRepository.findByProductId(productEntity.getId());
        long totalQuantity = productDetails.stream().mapToLong(ProductDetailEntity::getQuantity).sum();

        // If total quantity of product details is 0, set product status to 0 (inactive)
        if (totalQuantity == 0) {
            productEntity.setStatus(0);  // Set the product status to inactive
            productRepository.save(productEntity);  // Update the product status
        }

        return "Cập nhật sản phẩm thành công!";
    }

    @Override
    public String deleteProduct(String id) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        // Fetch all ProductDetailEntities associated with the ProductEntity
        List<ProductDetailEntity> productDetails = productDetailRepository.findByProductId(id);
        System.out.println("Found ProductDetailEntities: " + productDetails.size()); // Log the size

        // Delete each ProductDetailEntity if they exist
        if (!productDetails.isEmpty()) {
            productDetailRepository.deleteAll(productDetails);
            System.out.println("Deleted ProductDetailEntities for product ID: " + id); // Log the deletion
        }

        // Now delete the ProductEntity
        productRepository.deleteById(id);
        return "Xóa thành công!";
    }


    @Override
    public Page<ProductListDTO> getProductManagementResponse(FindProductManagementDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize());

        Page<ProductManagementResponse> pageResponse = productRepository.getProductManagementResponse(pageable, req);

        // Map ProductManagementResponse to ProductListDTO
        Page<ProductListDTO> pageDTO = pageResponse.map(productManagementResponse ->
                modelMapper.map(productManagementResponse, ProductListDTO.class)
        );

        return pageDTO;
    }

    @Override
    public String updateStatus(String id, Integer status) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        ProductEntity productEntity = existingProduct.get();
        productEntity.setStatus(status);
        productRepository.save(productEntity);

        return "Cập nhật trạng thái thành công!";
    }

    @Override
    public String updateNameAndStatus(String id, String name, Integer status) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        ProductEntity productEntity = existingProduct.get();
        productEntity.setName(name);
        productEntity.setStatus(status);
        productRepository.save(productEntity);

        return "Cập nhật tên và trạng thái thành công!";
    }

    @Override
    public ProductStatsDTO getProductStatistics() {
        // Tổng số sản phẩm (dựa trên ProductEntity)
        long totalProducts = productRepository.countByDeleteFlag(0);

        // Số sản phẩm đã bán (dựa trên BillDetailEntity)
        Long soldProducts = billDetailRepository.getTotalSoldQuantity();
        if (soldProducts == null) soldProducts = 0L;

        // Số sản phẩm còn lại (dựa trên ProductDetailEntity)
        Long remainingProducts = productDetailRepository.getTotalRemainingQuantity();
        if (remainingProducts == null) remainingProducts = 0L;

        return new ProductStatsDTO(totalProducts, soldProducts, remainingProducts);
    }

    @Override
    public List<ProductStatisticsResponse> getTopMostSoldProducts() {
        return productRepository.getTopMostSoldProducts();
    }



}
