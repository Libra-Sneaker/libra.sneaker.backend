package com.web.librasneaker.business.admin.typeManagement.service;

import com.web.librasneaker.entity.TypeEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface TypeManagementService {
    // create a new TypeManagementService
    String createType (@Valid TypeEntity type);

    // update a TypeManagementService
    String updateType (@Valid TypeEntity type);

    // delete a TypeManagementService
    String deleteType (String id);

    // get all TypeManagementService by id
    List<TypeEntity> getAllTypes();
}
