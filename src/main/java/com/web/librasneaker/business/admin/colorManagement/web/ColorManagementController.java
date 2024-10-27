package com.web.librasneaker.business.admin.colorManagement.web;

import com.web.librasneaker.business.admin.colorManagement.service.ColorManagementService;
import com.web.librasneaker.dto.colorManagement.CreateColorDTO;
import com.web.librasneaker.dto.colorManagement.UpdateColorDTO;
import com.web.librasneaker.entity.ColorEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/colorManagement")
public class ColorManagementController {
    private final ColorManagementService colorManagementService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createColor (@RequestBody CreateColorDTO request) {
        return ResponseEntity.ok().body(colorManagementService.createColor(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateColor (@RequestBody UpdateColorDTO request) {
        return ResponseEntity.ok().body(colorManagementService.updateColor(request));
    }

    @DeleteMapping("delete/{id}")
    public String deleteColor (@PathVariable String id) {
        return colorManagementService.deleteColor(id);
    }

    @GetMapping("get-all-color")
    public ResponseEntity<List<ColorEntity>> getAllColors () {
        return ResponseEntity.ok().body(colorManagementService.getAllColor());
    }
}
