package com.web.librasneaker.business.admin.brandManagement.service;

import com.web.librasneaker.dto.brandManagement.CreateBrandRequestDTO;
import com.web.librasneaker.dto.brandManagement.UpdateBrandRequestDTO;
import com.web.librasneaker.entity.BrandEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface BrandManagementService {
    // create brand
    Boolean createBrand (@Valid CreateBrandRequestDTO request);

    // update brand
    Boolean updateBrandRequest ( UpdateBrandRequestDTO updateBrandRequestDTO);

    // delete brand
    String deleteBrandRequestDTO (String id);

    // get all brands
    List<BrandEntity> getAllBrand ();
}
