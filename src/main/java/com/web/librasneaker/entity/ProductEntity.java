package com.web.librasneaker.entity;

import com.web.librasneaker.config.constant.classconstant.EntityProperties;
import com.web.librasneaker.entity.base.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class ProductEntity extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String id;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column
    private String status;

    @Column
    private String urlImage;

    @Column(name = "brand_id", length = EntityProperties.LENGTH_ID)
    private String brandId;

    @Column(name = "material_id", length = EntityProperties.LENGTH_ID)
    private String materialId;

    @Column(name = "type_id", length = EntityProperties.LENGTH_ID)
    private String typeId;

}
