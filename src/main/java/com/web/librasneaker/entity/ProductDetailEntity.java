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
@Table(name = "product_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class ProductDetailEntity extends PrimaryEntity {

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "size_id",length = EntityProperties.LENGTH_ID)
    private String sizeId;

    @Column(name = "color_id",length = EntityProperties.LENGTH_ID)
    private String colorId;

    @Column(name = "product_id",length = EntityProperties.LENGTH_ID)
    private String productId;

    @Column(name = "product_code",length = EntityProperties.LENGTH_CODE)
    private String productCode;

    @Column
    private Double price;

    @Column
    private Long quantity;

    @Column
    private String status;

}
