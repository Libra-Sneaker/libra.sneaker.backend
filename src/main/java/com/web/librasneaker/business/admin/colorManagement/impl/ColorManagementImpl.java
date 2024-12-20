package com.web.librasneaker.business.admin.colorManagement.impl;

import com.web.librasneaker.business.admin.colorManagement.service.ColorManagementService;
import com.web.librasneaker.config.exception.custom.NotFoundException;
import com.web.librasneaker.dto.colorManagement.CreateColorDTO;
import com.web.librasneaker.dto.colorManagement.UpdateColorDTO;
import com.web.librasneaker.dto.colorManagement.UpdateDeleteFlagColorDTO;
import com.web.librasneaker.entity.ColorEntity;
import com.web.librasneaker.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class ColorManagementImpl implements ColorManagementService {

    private final ColorRepository colorRepository;

    @Override
    public Boolean createColor(CreateColorDTO createColor) {

        Optional<ColorEntity> existingColor = colorRepository.findColorEntitiesByName(createColor.getName());
        if (existingColor.isPresent()) {
            throw new RuntimeException("Màu đã tồn tại");
        }

        ColorEntity color = new ColorEntity();
        color.setName(createColor.getName());
        color.setStatus(1);
        color.setDeleteFlag(0);

        colorRepository.save(color);

        return true;
    }

    @Override
    public Boolean updateColor(UpdateColorDTO updateColor) {

        Optional<ColorEntity> color = colorRepository.findById(updateColor.getId());
        if (!color.isPresent()) {
            throw new RuntimeException("Màu không tồn tại");
        }

        color.get().setName(updateColor.getName());
        color.get().setStatus(updateColor.getStatus());
        color.get().setDeleteFlag(updateColor.getDeleteFlag());

        colorRepository.save(color.get());
        return true;
    }

    @Override
    public String deleteColor(String id) {

        Optional<ColorEntity> color = colorRepository.findById(id);
        if (!color.isPresent()) {
            throw new NotFoundException();
        }

        colorRepository.deleteById(id);

        return "Xóa màu thành công!";
    }

    @Override
    public List<ColorEntity> getAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public String updateDeleteFlagColor(UpdateDeleteFlagColorDTO request) {
        Optional<ColorEntity> color = colorRepository.findById(request.getId());
        if (!color.isPresent()) {
            throw new RuntimeException("Màu không tồn tại");
        }

        color.get().setDeleteFlag(request.getDeleteFlag());
        colorRepository.save(color.get());


        return "Đặt cờ thành công!";
    }


}
