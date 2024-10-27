package com.web.librasneaker.business.admin.brandManagement.web;

import com.web.librasneaker.dto.brandManagement.CreateBrandRequestDTO;
import com.web.librasneaker.dto.brandManagement.UpdateBrandRequestDTO;
import com.web.librasneaker.business.admin.brandManagement.service.BrandManagementService;
import com.web.librasneaker.entity.BrandEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/brandManagement")
public class BrandManagementServiceController {
    private final BrandManagementService brandManagementService;

    @PostMapping("/create")
    public ResponseEntity<String> createBrand(@Valid @RequestBody CreateBrandRequestDTO request) {
        System.out.println("Received request: " + request);
        try {
            boolean isCreated = brandManagementService.createBrand(request);
            if (isCreated) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create brand");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBrand(@Valid @RequestBody UpdateBrandRequestDTO updateRequest) {
        try {
            boolean isUpdated = brandManagementService.updateBrandRequest(updateRequest);
            if (isUpdated) {
                return ResponseEntity.ok("Brand updated successfully");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update brand");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable String id) {
        String result = brandManagementService.deleteBrandRequestDTO(id);
        return ResponseEntity.ok(result); // Returns 200 OK with the message
    }

    @GetMapping("get-all-brand")
    public ResponseEntity<List<BrandEntity>> getAllBrand() {
        List<BrandEntity> brands = brandManagementService.getAllBrand();
        return new ResponseEntity<>(brands,HttpStatus.OK);
    }


}
