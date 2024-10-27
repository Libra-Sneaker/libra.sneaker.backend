package com.web.librasneaker.business.admin.sizeManagement.service;

import com.web.librasneaker.entity.SizeEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface SizeManagementService {
    // create a new size management service
    String createSize (@Valid SizeEntity size);

    // update an existing size management service
    String updateSize (@Valid SizeEntity size);

    // delete an existing size management service
    String deleteSize (String id);

    // get all size
    List<SizeEntity> getAllSize ();
}
