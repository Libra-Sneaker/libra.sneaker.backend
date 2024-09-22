package com.web.librasneaker.config.listener;

import com.web.librasneaker.entity.base.PrimaryEntity;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class CreatePrimaryEntityListener {

    @PrePersist
    private void onCreate(PrimaryEntity entity) {
        entity.setId(UUID.randomUUID().toString());
    }
}
