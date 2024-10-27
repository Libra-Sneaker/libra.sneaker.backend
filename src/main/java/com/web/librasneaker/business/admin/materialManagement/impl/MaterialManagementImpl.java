package com.web.librasneaker.business.admin.materialManagement.impl;

import com.web.librasneaker.business.admin.materialManagement.service.MaterialManagementService;
import com.web.librasneaker.entity.MaterialEntity;
import com.web.librasneaker.repository.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class MaterialManagementImpl implements MaterialManagementService {
    private final MaterialRepository materialRepository;


    @Override
    public String createMaterial(MaterialEntity materialEntity) {
        Optional<MaterialEntity> existingMaterial = materialRepository.findMaterialEntityByName(materialEntity.getName());
        if (existingMaterial.isPresent()) {
            throw new RuntimeException("Material đã tồn tại");
        }

        MaterialEntity material = new MaterialEntity();
        material.setName(materialEntity.getName());
        material.setDescription(materialEntity.getDescription());
        material.setStatus(materialEntity.getStatus());
        materialRepository.save(material);


        return "Thêm Material thành công!";
    }

    @Override
    public String updateMaterial(MaterialEntity materialEntity) {

        Optional<MaterialEntity> existingMaterial = materialRepository.findById(materialEntity.getId());
        if (!existingMaterial.isPresent()) {
            throw new RuntimeException("Material không tồn tại");
        }

        MaterialEntity material = existingMaterial.get();
        material.setName(materialEntity.getName());
        material.setDescription(materialEntity.getDescription());
        material.setStatus(materialEntity.getStatus());
        materialRepository.save(material);

        return "Cập nhật Material thành công!";
    }

    @Override
    public String deleteMaterial(String id) {
        Optional<MaterialEntity> existingMaterial = materialRepository.findById(id);
        if (!existingMaterial.isPresent()) {
            throw new RuntimeException("Material không tồn tại");
        }
        materialRepository.deleteById(id);
        return "Xoá Material thành công!";
    }

    @Override
    public List<MaterialEntity> getAllMaterialServices() {
        return materialRepository.findAll();
    }
}
