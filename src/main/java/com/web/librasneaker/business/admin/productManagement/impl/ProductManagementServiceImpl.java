package com.web.librasneaker.business.admin.productManagement.impl;

import com.web.librasneaker.business.admin.productManagement.service.ProductManagementService;
import com.web.librasneaker.dto.productManagement.CreateProductManagementDTO;
import com.web.librasneaker.dto.productManagement.FindProductManagementDTO;
import com.web.librasneaker.dto.productManagement.ProductListDTO;
import com.web.librasneaker.dto.productManagement.ProductManagementResponse;
import com.web.librasneaker.dto.productManagement.UpdateProductManagementDTO;
import com.web.librasneaker.entity.BrandEntity;
import com.web.librasneaker.entity.MaterialEntity;
import com.web.librasneaker.entity.ProductEntity;
import com.web.librasneaker.entity.TypeEntity;
import com.web.librasneaker.repository.BrandRepository;
import com.web.librasneaker.repository.MaterialRepository;
import com.web.librasneaker.repository.ProductRepository;
import com.web.librasneaker.repository.TypeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Valid
@AllArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {



    @Autowired
    private ModelMapper modelMapper;

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final MaterialRepository materialRepository;
    private final TypeRepository typeRepository;


    @Override
    public String createProduct(CreateProductManagementDTO request) {
        // Kiểm tra tên sản phẩm đã tồn tàij chưa?
        Optional<ProductEntity> existingProduct = productRepository.getProductEntitiesByName(request.getName());
        if (existingProduct.isPresent()) {
            throw new RuntimeException("Sản phẩm đã tồn tại");
        }

        Optional<BrandEntity> brandFind = brandRepository.findById(request.getBrandId());
        if(!brandFind.isPresent()){
            throw new RuntimeException("Thương hiệu không tồn tại");
        }

        Optional<MaterialEntity> materialFind = materialRepository.findById(request.getMaterialId());
        if (!materialFind.isPresent()){
            throw new RuntimeException("Chất liệu không tồn tại");
        }

        Optional<TypeEntity> typeFind = typeRepository.findById(request.getTypeId());
        if (!typeFind.isPresent()){
            throw new RuntimeException("Loại sản phẩm không tồn tại");
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setStatus(request.getStatus());
        productEntity.setBrandId(request.getBrandId());
        productEntity.setMaterialId(request.getMaterialId());
        productEntity.setTypeId(request.getTypeId());

        productRepository.save(productEntity);


        return "Thêm sản phẩm thành công!";
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

        productRepository.save(productEntity);



        return "Cập nhật sản phẩm thành công!";
    }

    @Override
    public String deleteProduct(String id) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()){
            throw new RuntimeException("Sản phẩm không tồn tại");
        }
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



}
