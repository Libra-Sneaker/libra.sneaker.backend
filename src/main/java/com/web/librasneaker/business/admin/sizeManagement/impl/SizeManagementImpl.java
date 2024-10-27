package com.web.librasneaker.business.admin.sizeManagement.impl;

import com.web.librasneaker.business.admin.sizeManagement.service.SizeManagementService;
import com.web.librasneaker.entity.SizeEntity;
import com.web.librasneaker.repository.SizeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Valid
public class SizeManagementImpl implements SizeManagementService {

    private  final SizeRepository sizeRepository;

    @Override
    public String createSize(SizeEntity size) {
        Optional<SizeEntity> existingSize = sizeRepository.getSizeEntityByName(size.getName());
        if(existingSize.isPresent()){
            throw new RuntimeException("Size đã tồn tại");
        }

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setName(size.getName());
        sizeEntity.setDescription(size.getDescription());
        sizeEntity.setStatus(size.getStatus());

        sizeRepository.save(sizeEntity);

        return "Thêm size thành công!";
    }

    @Override
    public String updateSize(SizeEntity size) {
        Optional<SizeEntity> existingSize = sizeRepository.findById(size.getId());
        if(!existingSize.isPresent()){
            throw new RuntimeException("Size không tồn tại");
        }

        SizeEntity sizeEntity = existingSize.get();
        sizeEntity.setName(size.getName());
        sizeEntity.setDescription(size.getDescription());
        sizeEntity.setStatus(size.getStatus());

        sizeRepository.save(sizeEntity);

        return "Cập nhật size thành công!";
    }

    @Override
    public String deleteSize(String id) {
        Optional<SizeEntity> existingSize = sizeRepository.findById(id);
        if(!existingSize.isPresent()){
            throw new RuntimeException("Size không tồn tại");
        }
        sizeRepository.deleteById(id);
        return "Xoá size thành công";
    }

    @Override
    public List<SizeEntity> getAllSize() {
        return sizeRepository.findAll();
    }
}
