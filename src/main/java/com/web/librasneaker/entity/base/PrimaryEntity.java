package com.web.librasneaker.entity.base;

import com.web.librasneaker.config.listener.CreatePrimaryEntityListener;
import com.web.librasneaker.config.constant.classconstant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity
        implements IsIdentified {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;
}