package com.web.librasneaker.business.admin.brandManagement.impl;

import com.web.librasneaker.config.exception.custom.NotFoundException;
import com.web.librasneaker.dto.brandManagement.CreateBrandRequestDTO;
import com.web.librasneaker.dto.brandManagement.UpdateBrandRequestDTO;
import com.web.librasneaker.business.admin.brandManagement.service.BrandManagementService;
import com.web.librasneaker.config.exception.custom.RestApiException;
import com.web.librasneaker.entity.BrandEntity;
import com.web.librasneaker.repository.BrandRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class BrandManagementServiceImpl implements BrandManagementService {

    private final BrandRepository brandRepository;

    @Override
    public Boolean createBrand(@Valid CreateBrandRequestDTO request) {
        Optional<BrandEntity> existingBrand = brandRepository.findBrandEntityByName(request.getName());
        if (existingBrand.isPresent()) {
            throw new RestApiException("Brand đã tồn tại");
        }

        BrandEntity brand = new BrandEntity();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setStatus(request.getStatus());

        brandRepository.save(brand);

        return true;
    }

    @Override
    public Boolean updateBrandRequest(UpdateBrandRequestDTO updateBrandRequestDTO) {

        Optional<BrandEntity> brand = brandRepository.findById(updateBrandRequestDTO.getId());
        if (!brand.isPresent()) {
            throw new RestApiException("Brand không tồn tại");
        }

        BrandEntity brandEntity = brand.get();
        brandEntity.setName(updateBrandRequestDTO.getName());
        brandEntity.setDescription(updateBrandRequestDTO.getDescription());
        brandEntity.setStatus(updateBrandRequestDTO.getStatus());

        brandRepository.save(brandEntity);

        return true;
    }

    @Override
    public String deleteBrandRequestDTO(String id) {
        Optional<BrandEntity> brand = brandRepository.findById(id);
        if(!brand.isPresent()) {
        throw new NotFoundException();
        }

        brandRepository.deleteById(brand.get().getId());

        return "Xóa thành công!";
    }

    @Override
    public List<BrandEntity> getAllBrand() {
        return brandRepository.findAll();
    }

}
