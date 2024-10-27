package com.web.librasneaker.business.admin.typeManagement.impl;

import com.web.librasneaker.business.admin.typeManagement.service.TypeManagementService;
import com.web.librasneaker.entity.TypeEntity;
import com.web.librasneaker.repository.TypeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Valid
@Service
public class TypeManagementIpml implements TypeManagementService {
    private final TypeRepository typeRepository;


    @Override
    public String createType(TypeEntity type) {
        Optional<TypeEntity> existingType = typeRepository.getTypeEntityByName(type.getName());
        if (existingType.isPresent()) {
            throw new IllegalArgumentException("Type đã tồn tại");
        }

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(type.getName());
        typeEntity.setDescription(type.getDescription());
        typeEntity.setStatus(type.getStatus());

        typeRepository.save(typeEntity);

        return "Thêm loại sản phẩm thành công!";
    }

    @Override
    public String updateType(TypeEntity type) {
        Optional<TypeEntity> existingType = typeRepository.findById(type.getId());
        if (!existingType.isPresent()) {
            throw new IllegalArgumentException("Loại sản phẩm không tồn tại");
        }

        TypeEntity typeEntity = existingType.get();
        typeEntity.setName(type.getName());
        typeEntity.setDescription(type.getDescription());
        typeEntity.setStatus(type.getStatus());

        typeRepository.save(typeEntity);
        return "Cập nhật thành công!";
    }

    @Override
    public String deleteType(String id) {
        Optional<TypeEntity> existingType = typeRepository.findById(id);
        if (!existingType.isPresent()) {
            throw new IllegalArgumentException("Loại sản phẩm không tồn tại");
        }

        typeRepository.deleteById(id);
        return "Xóa thành công!";
    }

    @Override
    public List<TypeEntity> getAllTypes() {
        return typeRepository.findAll();
    }
}
