package com.web.librasneaker.business.admin.colorManagement.service;

import com.web.librasneaker.dto.colorManagement.CreateColorDTO;
import com.web.librasneaker.dto.colorManagement.UpdateColorDTO;
import com.web.librasneaker.dto.colorManagement.UpdateDeleteFlagColorDTO;
import com.web.librasneaker.entity.ColorEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface ColorManagementService {

    // create a new color management service
    Boolean createColor (@Valid CreateColorDTO createColor);

    // update an existing color management service
    Boolean updateColor ( UpdateColorDTO updateColor);

    // delete a color management service
    String deleteColor ( String id);

    // get all color management services
    List<ColorEntity> getAllColor ();

    String updateDeleteFlagColor (UpdateDeleteFlagColorDTO request);

}
